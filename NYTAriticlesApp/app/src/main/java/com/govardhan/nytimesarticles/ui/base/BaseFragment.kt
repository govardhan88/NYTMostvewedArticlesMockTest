package com.govardhan.nytimesarticles.ui.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T, K : BaseViewModel<T>> : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: K

    private val stateObserver = Observer<T> { state ->
        state?.let {
            render(it)
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel = getFragmentViewModel()
        observeViewModel()
    }

    /**
     * We should force ourselves to implement this method in certain Fragment implementations
     *  @return Specific ViewModel class implementation
     *  //TODO: Unify view model creation without using abstract function (factory?)
     */
    protected abstract fun getFragmentViewModel(): K

    fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    protected abstract fun render(viewState: T)
}