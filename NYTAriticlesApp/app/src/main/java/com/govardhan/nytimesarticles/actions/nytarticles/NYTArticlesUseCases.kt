package com.govardhan.nytimesarticles.actions.nytarticles

import com.govardhan.nytimesarticles.data.remote.pojo.MostviewedArticlesResponse
import io.reactivex.Single


interface NYTArticlesUseCases {
    fun getMostViewedArticles(period: String): Single<MostviewedArticlesResponse>
}