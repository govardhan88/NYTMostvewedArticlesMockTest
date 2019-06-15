package com.govardhan.nytimesarticles.actions.nytarticles

import com.govardhan.nytimesarticles.common.utils.singleRequest
import com.govardhan.nytimesarticles.data.remote.NetworkingController
import com.govardhan.nytimesarticles.data.remote.pojo.MostviewedArticlesResponse
import io.reactivex.Single
import javax.inject.Inject

class NYTArticlesnteractor @Inject constructor(private val networkingController: NetworkingController) : NYTArticlesUseCases {


    override fun getMostViewedArticles(period: String): Single<MostviewedArticlesResponse> = singleRequest(networkingController.netApi.getMostViewedArticles("mostviewed/all-sections/$period"), networkingController)
}