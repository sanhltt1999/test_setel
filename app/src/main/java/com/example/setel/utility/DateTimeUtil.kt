package com.example.setel.utility

import java.util.*

object DateTimeUtil {

    const val AM = "am"
    const val PM = "pm"

    private val DAY_IN_WEEK = mapOf(
        2 to "Mon",
        3 to "Tue",
        4 to "Wed",
        5 to "Thu",
        6 to "Fri",
        7 to "Sat",
        8 to "Sun"
    )

    private val DATE_IN_WEEK = mapOf(
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

    fun getTimeFromMinutes(minutes: Int): String {
        val hours = minutes / 60
        val minute = minutes - hours * 60
        return if (hours <= 12) {
            "${if (hours >= 10) hours else "0$hours"}:${if (minute >= 10) minute else "0$minute"} $AM"
        } else {
            "${if (hours - 12 >= 10) hours - 12 else "0${hours - 12}"}:${if (minute >= 10) minute else "0$minute"} $PM"
        }
    }

    fun getTimeMinutesFromSecond(timeSecond: Long): Int {
        val time = Calendar.getInstance()
        time.time = Date(timeSecond * 1000)
        val currentHours = time.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = time.get(Calendar.MINUTE)
        return currentHours * 60 + currentMinutes
    }

    fun getDayFromSecond(timeSecond: Long): String {
        val time = Calendar.getInstance()
        time.time = Date(timeSecond * 1000)
        return DAY_IN_WEEK[time.get(Calendar.DAY_OF_WEEK)] ?: ""
    }

    fun convertTimeToMinutes(time: String): Int {
        val times = time.split(" ")
        val hoursAndMinute = times.first().split(":")
        return if (times.last() == AM) {
            hoursAndMinute.first().toInt() * 60 + hoursAndMinute.last().toInt()
        } else {
            hoursAndMinute.first().toInt() * 60 + hoursAndMinute.last().toInt() + 12 * 60
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