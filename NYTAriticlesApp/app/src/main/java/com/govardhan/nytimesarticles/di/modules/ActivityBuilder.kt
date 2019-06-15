package com.govardhan.nytimesarticles.di.modules

import com.govardhan.nytimesarticles.ui.nytarticles.NYTArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindNYTArticlesActivity(): NYTArticlesActivity


}