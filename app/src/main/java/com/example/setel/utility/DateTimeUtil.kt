package com.example.setel.utility

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeUtil {

    private const val HH_MM_A = "hh:mm a"

    val DAY_IN_WEEK = mapOf(
        2 to "Mon",
        3 to "Tue",
        4 to "Wed",
        5 to "Thu",
        6 to "Fri",
        7 to "Sat",
        8 to "Sun"
    )

    val DATE_IN_WEEK = mapOf(
        "Mon" to 2,
        "Tue" to 3,
        "Wed" to 4,
        "Thu" to 5,
        "Fri" to 6,
        "Sat" to 7,
        "Sun" to 8
    )

    private fun getDateInWeek(date: String): Int? {
        return DATE_IN_WEEK[date]
    }

    private fun getDayInWeek(day: Int): String? {
        return DAY_IN_WEEK[day]
    }

    fun convertLocalTimeToString(time: LocalTime): String {
        return time.format(DateTimeFormatter.ofPattern(HH_MM_A)).toString()
    }

    fun getLocalTimeFromSecond(timeSecond: Long): LocalTime {
        val time = Calendar.getInstance()
        time.time = Date(timeSecond * 1000)
        val currentHours = time.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = time.get(Calendar.MINUTE)
        val currentSecond = time.get(Calendar.SECOND)
        return LocalTime.of(currentHours, currentMinutes, currentSecond)
    }

    fun getDayFromSecond(timeSecond: Long): String {
        val time = Calendar.getInstance()
        time.time = Date(timeSecond * 1000)
        return DAY_IN_WEEK[time.get(Calendar.DAY_OF_WEEK)] ?: ""
    }

    fun convertStringToLocalTime(time: String): LocalTime {
        val times = time.split(" ")
        val hoursAndMinute = times.first().split(":")
        return if (hoursAndMinute.first().length == 1) {
            LocalTime.parse("0$time".trim(), DateTimeFormatter.ofPattern(HH_MM_A))
        } else {
            LocalTime.parse(time.trim(), DateTimeFormatter.ofPattern(HH_MM_A))

        }
    }

    fun getDateFromOperatingTimes(time: String): List<String> {
        val times = time.split("-")
        return if (times.size == 1) {
            listOf(times.first().toString())
        } else {
            val days = mutableListOf<String>()
            val dayStart = getDateInWeek(times.first()) ?: 0
            val dayEnd = getDateInWeek(times.last()) ?: 0
            for (i in dayStart..dayEnd) {
                getDayInWeek(i)?.let {
                    days.add(it)
                }
            }
            days
        }
    }
}