package com.example.setel.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val subscription: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }

    fun setLoading(isLoading: Boolean) {
        _dataLoading.value = isLoading
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return subscription
    }

    fun setError() {
        _error.value = Unit
    }

}