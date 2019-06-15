package com.govardhan.nytimesarticles.di.modules

import com.govardhan.nytimesarticles.ui.nytarticles.NYTArticlesItemFragment
import com.govardhan.nytimesarticles.ui.nytarticles.NYTArticlesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindNYTArticlesListFragment(): NYTArticlesListFragment


    @ContributesAndroidInjector
    abstract fun bindNYTArticlesItemFragment(): NYTArticlesItemFragment
}