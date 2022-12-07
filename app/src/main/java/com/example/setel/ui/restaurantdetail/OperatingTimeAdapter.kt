package com.example.setel.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.setel.databinding.LayoutOperatingTimeItemBinding
import com.example.setel.databinding.LayoutOperatingTimeWithStatusItemBinding
import com.example.setel.ui.home.model.OperatingTimeModel
import java.util.concurrent.Executors

class OperatingTimeAdapter : ListAdapter<OperatingTimeModel, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(OperatingTimeDiffCallback())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    companion object {
        private const val OPERATING_TIME = 0
        private const val OPERATING_TIME_WITH_STATUS = 1

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).timeClose != null) {
            OPERATING_TIME
        } else {
            OPERATING_TIME_WITH_STATUS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            OPERATING_TIME -> {
                val binding =
                    LayoutOperatingTimeItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                OperatingTimeViewHolder(binding)
            }
            else -> {
                val binding =
                    LayoutOperatingTimeWithStatusItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                OperatingTimeWithStatusViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val operatingTime = getItem(position)
        when (holder.itemViewType) {
            OPERATING_TIME -> if (holder is OperatingTimeViewHolder) holder.onBind(operatingTime)
            else -> if (holder is OperatingTimeWithStatusViewHolder) holder.onBind(operatingTime)
        }
    }


    inner class OperatingTimeViewHolder(private val itemBinding: LayoutOperatingTimeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(item: OperatingTimeModel) {
            itemBinding.operatingTime = item
        }
    }

    inner class OperatingTimeWithStatusViewHolder(private val itemBinding: LayoutOperatingTimeWithStatusItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(item: OperatingTimeModel) {
            itemBinding.operatingTime = item
        }
    }

    class OperatingTimeDiffCallback :
        DiffUtil.ItemCallback<OperatingTimeModel>() {

        override fun areItemsTheSame(
            oldItem: OperatingTimeModel,
            newItem: OperatingTimeModel,
        ): Boolean {
            return oldItem.day == newItem.day
                    && oldItem.timeClose == newItem.timeClose
                    && oldItem.timeStart == newItem.timeStart
        }

        override fun areContentsTheSame(
            oldItem: OperatingTimeModel,
            newItem: OperatingTimeModel,
        ): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }
    }
}