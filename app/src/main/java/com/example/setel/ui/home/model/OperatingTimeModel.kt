package com.example.setel.ui.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@Parcelize
data class RestaurantModel(
    val name: String = "",
    val operatingHours: List<OperatingTimeModel> = listOf(),
    val isOpenToday: Boolean = false,
) : Parcelable

@Parcelize
data class OperatingTimeModel(
    val dateOfWeek: Int = 0,
    val day: String = "",
    val timeStart: LocalTime? = null,
    val timeClose: LocalTime? = null,
) : Parcelable