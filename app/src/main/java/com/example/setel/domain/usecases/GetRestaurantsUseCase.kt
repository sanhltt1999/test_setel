package com.example.setel.domain.usecases

import com.example.setel.data.remote.entity.RestaurantsResponse
import com.example.setel.data.repository.AppRepositoryInterface
import io.reactivex.Single
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val appRepositoryInterface: AppRepositoryInterface,
) {

    fun invoke(): Single<RestaurantsResponse> {
        return appRepositoryInterface.getRestaurants().flatMap {
            Single.just(it)
        }
    }


}