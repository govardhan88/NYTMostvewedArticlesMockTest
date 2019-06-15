package com.govardhan.nytimesarticles.data.model

import android.os.Bundle
import com.google.gson.Gson
import com.govardhan.nytimesarticles.common.NYT_ARTICLE_ARG_KEY
import com.govardhan.nytimesarticles.common.events.RxBus
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem
import com.govardhan.nytimesarticles.ui.nytarticles.NYTArticlesItemFragment

data class NYTArticleModel(
    var title: String? = "",
    var byLine: String? = "",
    var source: String? = "",
    var publishDate: String? = "",
    var imageUrl: String? = "",
    var nytArticleItem: NYTArticleItem?=null
){
    fun gotoNytArticleDetails(nytArticleItem: NYTArticleItem?) {
        val bundle = Bundle()
        nytArticleItem?.let {
            bundle.putString(NYT_ARTICLE_ARG_KEY, Gson().toJson(it))
        }
        RxBus.switchFragment(NYTArticlesItemFragment::class.java, bundle)
    }

}