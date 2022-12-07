package com.example.setel.domain.usecases

import com.example.setel.data.repository.AppRepositoryInterface
import com.example.setel.ui.home.model.OperatingTimeModel
import com.example.setel.ui.home.model.RestaurantModel
import com.example.setel.utility.DateTimeUtil
import io.reactivex.Single
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val appRepositoryInterface: AppRepositoryInterface,
) {

    fun invoke(): Single<List<RestaurantModel>> {
        return appRepositoryInterface.getRestaurants().flatMap {

            val restaurants = mutableListOf<RestaurantModel>()
            val currentTime = DateTimeUtil.getLocalTimeFromSecond(it.timestamp)
            val currentDate = DateTimeUtil.getDayFromSecond(it.timestamp)
            it.restaurants.forEach { element ->

                val operatingHours = mapToOperatingHours(element.operatingHours)

                restaurants.add(
                    RestaurantModel(
                        name = element.name,
                        operatingHours = operatingHours,
                        isOpenToday = operatingHours.find { operatingHour -> operatingHour.day == currentDate && currentTime > operatingHour.timeStart && currentTime < operatingHour.timeClose } != null
                    )
                )
            }

            Single.just(restaurants)
        }
    }


    fun mapToOperatingHours(time: String): List<OperatingTimeModel> {
        val operatingDays = time.split(" / ")
        val operatingTimes = mutableListOf<OperatingTimeModel>()
        operatingDays.forEach {
            operatingTimes.addAll(getOperatingTimes(it))
        }

        (2..8).forEach { date ->
            if (operatingTimes.find { it.dateOfWeek == date } == null) {
                operatingTimes.add(OperatingTimeModel(
                    dateOfWeek = date,
                    day = DateTimeUtil.DAY_IN_WEEK[date] ?: "",

                    ))
            }
        }

        operatingTimes.sortBy { it.dateOfWeek }

        return operatingTimes
    }

    fun getDayFromOperatingHours(time: String): List<String> {
        val dayDetails = time.split(", ")

        if (dayDetails.isEmpty()) return listOf()

        val days = mutableListOf<String>()
        if (dayDetails.size > 1) {
            for (i in dayDetails.indices - 1) {
                days.addAll(DateTimeUtil.getDateFromOperatingTimes(dayDetails[i]))
            }
            days.addAll(
                DateTimeUtil.getDateFromOperatingTimes(
                    dayDetails.last().split(" ")
                        .first()
                )
            )
        } else {
            val day = dayDetails.first().split(" ").first()
            days.addAll(DateTimeUtil.getDateFromOperatingTimes(day))
        }

        return days
    }

    fun getOperatingTimes(time: String): List<OperatingTimeModel> {
        val days = getDayFromOperatingHours(time)

        val lastDaySubStrings = time.split(", ").last().split(" ")
        val times =
            time.split(", ").last().substring(lastDaySubStrings.first().length + 1).split(" - ")
        val timeStart =
            DateTimeUtil.convertStringToLocalTime(times.first().uppercase())
        val timeEnd =
            DateTimeUtil.convertStringToLocalTime(times.last().uppercase())

        val operatingTimes = mutableListOf<OperatingTimeModel>()
        days.forEach {
            operatingTimes.add(
                OperatingTimeModel(
                    dateOfWeek = DateTimeUtil.DATE_IN_WEEK[it] ?: 0,
                    day = it,
                    timeStart = timeStart,
                    timeClose = timeEnd
                )
            )
        }
        return operatingTimes
    }

}