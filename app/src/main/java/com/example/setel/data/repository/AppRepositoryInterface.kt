package com.example.setel.data.repository

import com.example.setel.data.remote.entity.RestaurantsResponse
import io.reactivex.Single

interface AppRepositoryInterface {
    fun getRestaurants(): Single<RestaurantsResponse>
}