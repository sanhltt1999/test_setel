package com.example.setel.data.remote

import com.example.setel.data.remote.entity.RestaurantsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("homework")
    fun getRestaurants(): Single<RestaurantsResponse>
}