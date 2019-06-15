package com.govardhan.nytimesarticles.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.govardhan.nytimesarticles.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}