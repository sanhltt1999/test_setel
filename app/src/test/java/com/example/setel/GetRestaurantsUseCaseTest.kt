package com.example.setel

import com.example.setel.domain.usecases.GetRestaurantsUseCase
import com.example.setel.ui.home.model.OperatingTimeModel
import com.example.setel.utility.DateTimeUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class GetRestaurantsUseCaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var getRestaurantsUseCase: GetRestaurantsUseCase

    @Test
    fun `Restaurant Opening Day Test`() {
        val operatingHours = "Mon-Sat 11:00 am - 11:00 pm"
        val operatingDays = listOf(
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat"
        )
        assertEquals(operatingDays, getRestaurantsUseCase.getDayFromOperatingHours(operatingHours))
    }

    @Test
    fun `Get OperatingTimes Test`() {
        val operatingHours = "Mon-Sat 11:00 am - 11:00 pm"
        val timeStart = DateTimeUtil.convertStringToLocalTime("11:00 am".uppercase())
        val timeClose = DateTimeUtil.convertStringToLocalTime("11:00 pm".uppercase())

        val operatingTimes = listOf(
            OperatingTimeModel(dateOfWeek = 2, day = "Mon", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 3, day = "Tue", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 4, day = "Wed", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 5, day = "Thu", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 6, day = "Fri", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 7, day = "Sat", timeStart = timeStart, timeClose = timeClose),
        )
        assertEquals(operatingTimes, getRestaurantsUseCase.getOperatingTimes(operatingHours))
    }

    @Test
    fun `Map To OperatingHours Test`() {
        val operatingHours = "Mon-Thu, Sun 11:00 am - 10:00 pm  / Fri-Sat 11:00 am - 11:00 pm"
        val timeStart = DateTimeUtil.convertStringToLocalTime("11:00 am".uppercase())

        val timeClose = DateTimeUtil.convertStringToLocalTime("10:00 pm".uppercase())
        val timeCloseSecond = DateTimeUtil.convertStringToLocalTime("11:00 pm".uppercase())

        val operatingTimes = listOf(
            OperatingTimeModel(dateOfWeek = 2, day = "Mon", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 3, day = "Tue", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 4, day = "Wed", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 5, day = "Thu", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 6, day = "Fri", timeStart = timeStart, timeClose = timeCloseSecond),
            OperatingTimeModel(dateOfWeek = 7, day = "Sat", timeStart = timeStart, timeClose = timeCloseSecond),
            OperatingTimeModel(dateOfWeek = 8, day = "Sun", timeStart = timeStart, timeClose = timeClose),
        )
        assertEquals(operatingTimes, getRestaurantsUseCase.mapToOperatingHours(operatingHours))
    }

    @Test
    fun `Map To OperatingHours Test 2`() {
        val operatingHours = "Mon-Thu, Sun 11:00 am - 10:00 pm"
        val timeStart = DateTimeUtil.convertStringToLocalTime("11:00 am".uppercase())

        val timeClose = DateTimeUtil.convertStringToLocalTime("10:00 pm".uppercase())

        val operatingTimes = listOf(
            OperatingTimeModel(dateOfWeek = 2, day = "Mon", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 3, day = "Tue", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 4, day = "Wed", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 5, day = "Thu", timeStart = timeStart, timeClose = timeClose),
            OperatingTimeModel(dateOfWeek = 6, day = "Fri"),
            OperatingTimeModel(dateOfWeek = 7, day = "Sat"),
            OperatingTimeModel(dateOfWeek = 8, day = "Sun", timeStart = timeStart, timeClose = timeClose),
        )
        assertEquals(operatingTimes, getRestaurantsUseCase.mapToOperatingHours(operatingHours))
    }
}
