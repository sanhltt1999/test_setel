package com.example.setel.data.repository

import com.example.setel.data.remote.ApiService
import com.example.setel.data.remote.entity.RestaurantsResponse
import io.reactivex.Single
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService,
) : AppRepositoryInterface {



    override fun getRestaurants(): Single<RestaurantsResponse> {
        return apiService.getRestaurants().onErrorResumeNext {
            Single.error(it)
        }
    }
}