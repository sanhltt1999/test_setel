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

            val totalMin = DateTimeUtil.getTimeMinutesFromSecond(it.timestamp)
            val currentDate = DateTimeUtil.getDayFromSecond(it.timestamp)
            it.restaurants.forEach { element ->

                val operatingHours = mapToOperatingHours(element.operatingHours)

                restaurants.add(
                    RestaurantModel(
                        name = element.name,
                        operatingHours = operatingHours,
                        isOpenToday = operatingHours.find { operatingHour -> operatingHour.day == currentDate && totalMin in operatingHour.timeStart..operatingHour.timeClose } != null
                    )
                )
            }

            Single.just(restaurants)
        }
    }

    private fun mapToOperatingHours(time: String): List<OperatingTimeModel> {
        val operatingDays = time.split(" / ")
        val operatingTimes = mutableListOf<OperatingTimeModel>()
        operatingDays.forEach {
            operatingTimes.addAll(getOperatingTimes(it))
        }
        return operatingTimes
    }

    private fun getDayFromOperatingHours(time: String): List<String> {
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

    private fun getOperatingTimes(time: String): List<OperatingTimeModel> {
        val days = getDayFromOperatingHours(time)

        val lastDaySubStrings = time.split(", ").last().split(" ")
        val times =
            time.split(", ").last().substring(lastDaySubStrings.first().length + 1).split(" - ")
        val timeStart = DateTimeUtil.convertTimeToMinutes(times.first())
        val timeEnd = DateTimeUtil.convertTimeToMinutes(times.last())

        val operatingTimes = mutableListOf<OperatingTimeModel>()
        days.forEach {
            operatingTimes.add(
                OperatingTimeModel(
                    day = it,
                    timeStart = timeStart,
                    timeClose = timeEnd
                )
            )
        }
        return operatingTimes
    }


}