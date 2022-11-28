package com.example.setel.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.setel.data.remote.entity.RestaurantsResponse
import com.example.setel.domain.usecases.GetRestaurantsUseCase
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

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _errorProducts = MutableLiveData<List<RestaurantsResponse>>()
    val errorProducts: LiveData<List<RestaurantsResponse>> = _errorProducts

    init {
        start()
    }

    private fun start() {
        if (_dataLoading.value == true) {
            return
        }
        _dataLoading.value = true
    }

    fun getErrorProducts() {
        _dataLoading.value = true
        subscription.add(getErrorProductUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ elements ->
                Log.d("Sanh Test", elements.toString())
            }
            ) { t: Throwable? ->
                _dataLoading.value = false
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

}
