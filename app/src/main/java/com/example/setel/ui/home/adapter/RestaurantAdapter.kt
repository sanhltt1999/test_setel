package com.example.setel.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.setel.R
import com.example.setel.databinding.LayoutRestaurantItemBinding
import com.example.setel.ui.home.model.RestaurantDiffCallback
import com.example.setel.ui.home.model.RestaurantModel

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var restaurants: List<RestaurantModel> = listOf()

    private var listener: OnRestaurantAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_restaurant_item, parent, false)
        return RestaurantViewHolder(LayoutRestaurantItemBinding.bind(view))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = restaurants.size

    inner class RestaurantViewHolder(private val itemBinding: LayoutRestaurantItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(item: RestaurantModel) {
            itemBinding.apply {
                restaurant = item
                root.setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }

    fun setList(restaurants: List<RestaurantModel>) {
        if (restaurants.isEmpty()) return
        val oldList = ArrayList(this.restaurants)
        this.restaurants = restaurants
        val diffCallback = RestaurantDiffCallback(oldList = oldList,
            newList = restaurants as ArrayList<RestaurantModel>)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        notifyItemRangeChanged(0, restaurants.size - 1)
    }

    fun setOnItemListener(listener: OnRestaurantAdapterListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.onBind(restaurants[position])
    }

    interface OnRestaurantAdapterListener {
        fun onItemClick(item: RestaurantModel)
    }
}