package com.example.setel.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.setel.domain.usecases.GetRestaurantsUseCase
import com.example.setel.ui.base.BaseViewModel
import com.example.setel.ui.home.model.RestaurantModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    private val _restaurants = MutableLiveData<List<RestaurantModel>>()
    val restaurants: LiveData<List<RestaurantModel>> = _restaurants

    @Inject
    lateinit var getErrorProductUseCase: GetRestaurantsUseCase

    fun getRestaurants() {
        setLoading(true)
        getCompositeDisposable().add(getErrorProductUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ elements ->
                _restaurants.value = elements
                setLoading(false)
            }
            ) { t: Throwable? ->
                setLoading(false)
                setError()
            })
    }
}
