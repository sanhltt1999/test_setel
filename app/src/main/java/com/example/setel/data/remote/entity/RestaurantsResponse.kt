package com.example.setel.data.remote.entity

import com.example.setel.domain.entity.RestaurantDetail
import com.example.setel.domain.entity.Restaurants

data class RestaurantsResponse(
    val timestamp: Long = 0L,
    val restaurants: List<RestaurantDetailResponse> = listOf(),
) {
    fun toEntity(): Restaurants = Restaurants(
        timestamp = timestamp,
        restaurants = restaurants.map { it.toEntity() }
    )
}

data class RestaurantDetailResponse(
    val name: String = "",
    val operatingHours: String = "",
) {
    fun toEntity(): RestaurantDetail = RestaurantDetail(
        name = name,
        operatingHours = operatingHours
    )
}