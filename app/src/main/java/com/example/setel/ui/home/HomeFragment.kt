package com.example.setel.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setel.R
import com.example.setel.coordinator.AppNavigator
import com.example.setel.databinding.FragmentHomeBinding
import com.example.setel.ui.base.BaseFragment
import com.example.setel.ui.home.adapter.RestaurantAdapter
import com.example.setel.ui.home.model.RestaurantModel
import com.example.setel.ui.home.viewmodel.MainViewModel
import com.example.setel.ui.restaurantdetail.RestaurantDetailFragment.Companion.RESTAURANT_EXTRA
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<MainViewModel>(), RestaurantAdapter.OnRestaurantAdapterListener {

    private val binding: FragmentHomeBinding by dataBinding()
    private val viewModel by viewModels<MainViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun viewModel(): MainViewModel = viewModel

    private val restaurantAdapter by lazy {
        RestaurantAdapter(this)
    }

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rclRestaurant.layoutManager = LinearLayoutManager(requireContext())
        binding.rclRestaurant.adapter = restaurantAdapter

        if (savedInstanceState == null) {
            viewModel.getRestaurants()
        }

        viewModel.restaurants.observe(viewLifecycleOwner) {
            restaurantAdapter.submitList(it)
        }
    }

    override fun onItemClick(item: RestaurantModel) {
        appNavigator.showForgotPasswordFragment(bundle = Bundle().apply {
            putParcelable(RESTAURANT_EXTRA, item)
        })
    }

}