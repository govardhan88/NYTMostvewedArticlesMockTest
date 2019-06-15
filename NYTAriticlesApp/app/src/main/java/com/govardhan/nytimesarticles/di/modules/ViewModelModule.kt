package com.govardhan.nytimesarticles.di.modules

import android.arch.lifecycle.ViewModel
import com.govardhan.nytimesarticles.ui.nytarticles.NYTArticlesListFragmentViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    
    @Binds
    @IntoMap
    @ViewModelKey(NYTArticlesListFragmentViewModel::class)
    abstract fun bindNYTArticlesListFragmentViewModel(viewModel: NYTArticlesListFragmentViewModel): ViewModel

}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)