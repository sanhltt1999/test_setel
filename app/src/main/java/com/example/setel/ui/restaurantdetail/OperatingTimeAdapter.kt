package com.example.setel.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.setel.R
import com.example.setel.databinding.LayoutOperatingTimeItemBinding
import com.example.setel.ui.home.model.OperatingTimeDiffCallback
import com.example.setel.ui.home.model.OperatingTimeModel

class OperatingTimeAdapter : RecyclerView.Adapter<OperatingTimeAdapter.OperatingTimeViewHolder>() {

    private var operatingTimes: List<OperatingTimeModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperatingTimeViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_operating_time_item, parent, false)
        return OperatingTimeViewHolder(LayoutOperatingTimeItemBinding.bind(view))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = operatingTimes.size

    inner class OperatingTimeViewHolder(private val itemBinding: LayoutOperatingTimeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(item: OperatingTimeModel) {
            itemBinding.operatingTime = item
        }
    }


    fun setList(operatingTimes: List<OperatingTimeModel>) {
        if (operatingTimes.isEmpty()) return
        val oldList = ArrayList(this.operatingTimes)
        this.operatingTimes = operatingTimes
        val diffCallback = OperatingTimeDiffCallback(oldList = oldList,
            newList = operatingTimes as ArrayList<OperatingTimeModel>)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        notifyItemRangeChanged(0, operatingTimes.size - 1)
    }

    override fun onBindViewHolder(
        holder: OperatingTimeAdapter.OperatingTimeViewHolder,
        position: Int,
    ) {
        holder.onBind(operatingTimes[position])
    }
}