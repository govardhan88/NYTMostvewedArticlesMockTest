package com.govardhan.nytimesarticles.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {
    var stateLiveData = MutableLiveData<T>()

    fun currentViewState(): T = stateLiveData.value!!
}

