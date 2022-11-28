package com.example.setel.data.repository

import com.example.setel.data.remote.ApiService
import com.example.setel.domain.entity.Restaurants
import io.reactivex.Single
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService,
) : AppRepositoryInterface {


    override fun getRestaurants(): Single<Restaurants> {
        return apiService.getRestaurants().map { it.toEntity() }.onErrorResumeNext {
            Single.error(it)
        }
    }
}