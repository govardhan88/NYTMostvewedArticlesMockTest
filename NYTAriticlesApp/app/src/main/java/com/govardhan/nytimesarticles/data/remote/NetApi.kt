package com.govardhan.nytimesarticles.data.remote

import com.govardhan.nytimesarticles.common.API_KEY
import com.govardhan.nytimesarticles.data.remote.pojo.MostviewedArticlesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NetApi {

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< POST REQUESTS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //For getMostViewedArticles to validate user
    @GET
    fun getMostViewedArticles(@Url period:String,
                              @Query("api-key") apiKey:String= API_KEY
    ): Single<MostviewedArticlesResponse>

}

