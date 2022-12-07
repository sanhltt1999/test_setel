package com.example.setel.domain.entity

data class Restaurants(
    val timestamp: Long = 0L,
    val restaurants: List<RestaurantDetail> = listOf(),
)

data class RestaurantDetail(
    val name: String = "",
    val operatingHours: String = "",
)