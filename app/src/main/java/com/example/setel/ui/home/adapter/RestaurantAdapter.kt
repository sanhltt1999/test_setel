package com.example.setel.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.setel.R
import com.example.setel.databinding.LayoutRestaurantItemBinding
import com.example.setel.ui.home.model.RestaurantModel
import java.util.concurrent.Executors

class RestaurantAdapter(val listener: OnRestaurantAdapterListener) :
    ListAdapter<RestaurantModel, RestaurantAdapter.RestaurantViewHolder>(
        AsyncDifferConfig.Builder(RestaurantDiffCallback())
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_restaurant_item, parent, false)
        return RestaurantViewHolder(LayoutRestaurantItemBinding.bind(view))
    }

    inner class RestaurantViewHolder(private val itemBinding: LayoutRestaurantItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(item: RestaurantModel) {
            itemBinding.apply {
                restaurant = item
                root.setOnClickListener {
                    listener.onItemClick(item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnRestaurantAdapterListener {
        fun onItemClick(item: RestaurantModel)
    }

    class RestaurantDiffCallback : DiffUtil.ItemCallback<RestaurantModel>() {

        override fun areItemsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean {
            return oldItem.name == newItem.name && oldItem.isOpenToday == newItem.isOpenToday
        }

        override fun areContentsTheSame(
            oldItem: RestaurantModel,
            newItem: RestaurantModel,
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.isOpenToday == newItem.isOpenToday
        }
    }
}