package com.example.setel.ui.restaurantdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setel.R
import com.example.setel.databinding.ActivityRestaurantDetailBinding
import com.example.setel.ui.home.model.RestaurantModel
import com.wada811.databinding.dataBinding

class RestaurantDetailActivity : AppCompatActivity() {

    private val binding: ActivityRestaurantDetailBinding by dataBinding()

    private val operatingTimeAdapter by lazy {
        OperatingTimeAdapter()
    }

    companion object {
        const val RESTAURANT_EXTRA = "restaurant_extra"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        val restaurant = intent.extras?.get(RESTAURANT_EXTRA) as RestaurantModel
        binding.restaurantName = restaurant.name

        binding.rclOperatingHours.layoutManager = LinearLayoutManager(this)
        binding.rclOperatingHours.adapter = operatingTimeAdapter

        operatingTimeAdapter.setList(restaurant.operatingHours)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}