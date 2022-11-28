package com.example.setel.domain.entity

import com.example.setel.data.remote.entity.RestaurantDetailResponse

data class Restaurants(
    val timestamp: Long,
    val restaurants: List<RestaurantDetail>
)

data class RestaurantDetail(
    val name: String,
    val operatingHours: String
)