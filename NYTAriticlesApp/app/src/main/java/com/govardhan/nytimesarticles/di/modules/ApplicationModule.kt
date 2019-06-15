package com.govardhan.nytimesarticles.di.modules

import com.govardhan.nytimesarticles.NYTimesArticlesApp
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class ApplicationModule {
    @Binds
    @Singleton
    abstract fun application(NYTimesArticlesApp: NYTimesArticlesApp): NYTimesArticlesApp
}