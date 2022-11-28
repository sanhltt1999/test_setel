package com.example.setel.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.setel.domain.usecases.GetRestaurantsUseCase
import com.example.setel.ui.home.model.RestaurantModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getErrorProductUseCase: GetRestaurantsUseCase
) : ViewModel() {
    private val subscription: CompositeDisposable = CompositeDisposable()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _restaurants = MutableLiveData<List<RestaurantModel>>()
    val restaurants: LiveData<List<RestaurantModel>> = _restaurants

    init {
        start()
    }

    private fun start() {
        if (_dataLoading.value == true) {
            return
        }
        _dataLoading.value = true
    }

    fun getRestaurants() {
        _dataLoading.value = true
        subscription.add(getErrorProductUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ elements ->
                _restaurants.value = elements
                _dataLoading.value = false
            }
            ) { t: Throwable? ->
                _dataLoading.value = false
                _error.value = Unit
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

}
