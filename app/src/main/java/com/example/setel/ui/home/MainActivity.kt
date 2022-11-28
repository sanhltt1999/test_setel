package com.example.setel.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setel.R
import com.example.setel.databinding.ActivityMainBinding
import com.example.setel.ui.dialog.ErrorDialog
import com.example.setel.ui.dialog.LoadingProgress
import com.example.setel.ui.home.adapter.RestaurantAdapter
import com.example.setel.ui.home.viewmodel.MainViewModel
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val binding: ActivityMainBinding by dataBinding()

    private val restaurantAdapter by lazy {
        RestaurantAdapter()
    }

    private val progress: LoadingProgress by lazy {
        LoadingProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.rclRestaurant.layoutManager = LinearLayoutManager(this)
        binding.rclRestaurant.adapter = restaurantAdapter

        viewModel.getRestaurants()

        viewModel.dataLoading.observe(this) {
            if (it) progress.showLoadingProgress(this) else progress.hideLoadingProgress()
        }

        viewModel.restaurants.observe(this) {
            restaurantAdapter.setList(it)
        }

        viewModel.error.observe(this) {
            ErrorDialog().show(supportFragmentManager, ErrorDialog::class.simpleName)
        }
    }
}