package com.govardhan.nytimesarticles.di

import com.govardhan.nytimesarticles.NYTimesArticlesApp
import com.govardhan.nytimesarticles.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    RepositoryModule::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class,
    UseCasesModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    ServiceBuilder::class,
    BroadcastBuilder::class,
    NetworkModule::class
])
interface ApplicationComponent : AndroidInjector<NYTimesArticlesApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NYTimesArticlesApp>()

}
