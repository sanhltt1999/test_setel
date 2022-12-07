package com.example.setel.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.setel.R
import com.example.setel.databinding.FragmentRestaurantDetailBinding
import com.example.setel.ui.home.model.RestaurantModel
import com.wada811.databinding.dataBinding

class RestaurantDetailFragment : Fragment() {

    private val binding: FragmentRestaurantDetailBinding by dataBinding()

    private val operatingTimeAdapter by lazy {
        OperatingTimeAdapter()
    }

    companion object {
        const val RESTAURANT_EXTRA = "restaurant_extra"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = arguments?.get(RESTAURANT_EXTRA) as RestaurantModel
        binding.restaurantName = restaurant.name

        binding.rclOperatingHours.layoutManager = LinearLayoutManager(requireContext())
        binding.rclOperatingHours.adapter = operatingTimeAdapter

        operatingTimeAdapter.submitList(restaurant.operatingHours)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}