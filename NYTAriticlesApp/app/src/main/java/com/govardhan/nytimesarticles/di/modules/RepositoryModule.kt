package com.govardhan.nytimesarticles.di.modules

import android.content.Context
import com.govardhan.nytimesarticles.NYTimesArticlesApp
import com.govardhan.nytimesarticles.data.runtime.appstate.AppStateRuntime
import dagger.Module
import dagger.Provides
import org.rekotlin.Store
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideContext(NYTimesArticlesApp: NYTimesArticlesApp): Context = NYTimesArticlesApp.applicationContext

    @Provides
    @Singleton
    fun provideAppStateStore(NYTimesArticlesApp: NYTimesArticlesApp): Store<AppStateRuntime> = NYTimesArticlesApp.getAppStateStore()
}