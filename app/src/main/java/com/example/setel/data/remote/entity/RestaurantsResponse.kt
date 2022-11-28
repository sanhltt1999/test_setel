package com.example.setel.data.remote.entity

data class RestaurantsResponse(
    val timestamp: Long,
    val restaurants: List<RestaurantDetailResponse>,
)

data class RestaurantDetailResponse(
    val name: String,
    val operatingHours: String,
)