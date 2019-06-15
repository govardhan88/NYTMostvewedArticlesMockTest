package com.govardhan.nytimesarticles.di.modules

import com.govardhan.nytimesarticles.actions.nytarticles.NYTArticlesUseCases
import com.govardhan.nytimesarticles.actions.nytarticles.NYTArticlesnteractor
import com.govardhan.nytimesarticles.data.remote.NetworkingController
import dagger.Module
import dagger.Provides

/**
 * Created by Antoni Castejón on 04/01/2018.
 */
@Module
class UseCasesModule {

    @Provides
    fun providesNYTArticlesUseCases(networkingController: NetworkingController): NYTArticlesUseCases = NYTArticlesnteractor(networkingController)

}