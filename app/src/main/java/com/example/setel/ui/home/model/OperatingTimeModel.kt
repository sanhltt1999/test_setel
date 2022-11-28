package com.example.setel.ui.home.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantModel(
    val name: String,
    val operatingHours: List<OperatingTimeModel>,
    val isOpenToday: Boolean,
) : Parcelable

@Parcelize
data class OperatingTimeModel(
    val day: String,
    val timeStart: Int,
    val timeClose: Int,
) : Parcelable

class RestaurantDiffCallback(
    private val oldList: ArrayList<RestaurantModel>,
    private val newList: ArrayList<RestaurantModel>,
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name && oldList[oldItemPosition].isOpenToday == newList[newItemPosition].isOpenToday
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition, newItemPosition)
    }
}

class OperatingTimeDiffCallback(
    private val oldList: ArrayList<OperatingTimeModel>,
    private val newList: ArrayList<OperatingTimeModel>,
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].day == newList[newItemPosition].day
                && oldList[oldItemPosition].timeClose == newList[newItemPosition].timeClose
                && oldList[oldItemPosition].timeStart == newList[newItemPosition].timeStart
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition, newItemPosition)
    }
}