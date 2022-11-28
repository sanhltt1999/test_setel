package com.example.setel.ui.home.model

import androidx.recyclerview.widget.DiffUtil

data class RestaurantModel(
    val name: String,
    val operatingHours: List<OperatingTimeModel>,
    val isOpenToday: Boolean
)

data class OperatingTimeModel(
    val day: String,
    val timeStart: Int,
    val timeClose: Int,
)

class RestaurantDiffCallback(private val oldList: ArrayList<RestaurantModel>, private val newList: ArrayList<RestaurantModel>) :
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