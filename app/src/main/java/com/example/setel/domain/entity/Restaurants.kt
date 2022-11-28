package com.example.setel.domain.entity

data class Restaurants(
    val timestamp: Long,
    val restaurants: List<RestaurantDetail>,
)

data class RestaurantDetail(
    val name: String,
    val operatingHours: String,
)