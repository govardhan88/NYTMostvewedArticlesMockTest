package com.govardhan.nytimesarticles

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ProcessLifecycleOwner
import android.content.Context
import android.support.multidex.MultiDex
import com.govardhan.nytimesarticles.data.runtime.appstate.AppStateRuntime
import com.govardhan.nytimesarticles.data.runtime.appstate.foregroundReducer
import com.govardhan.nytimesarticles.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasServiceInjector
import dagger.android.support.DaggerApplication
import org.rekotlin.Store

val appStateStore = Store(
        reducer = ::foregroundReducer,
        state = AppStateRuntime()
)

class NYTimesArticlesApp : DaggerApplication(), HasServiceInjector, LifecycleObserver {

    fun getAppStateStore(): Store<AppStateRuntime> = appStateStore

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

}