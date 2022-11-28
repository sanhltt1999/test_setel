package com.example.setel.data.repository

import com.example.setel.domain.entity.Restaurants
import io.reactivex.Single

interface AppRepositoryInterface {
    fun getRestaurants(): Single<Restaurants>
}