package com.govardhan.nytimesarticles.ui.nytarticles

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.govardhan.nytimesarticles.actions.nytarticles.NYTArticlesnteractor
import com.govardhan.nytimesarticles.data.remote.pojo.MostviewedArticlesResponse
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem
import com.govardhan.nytimesarticles.ui.base.BaseViewModel
import javax.inject.Inject

class NYTArticlesListFragmentViewModel @Inject constructor(private val nytArticlesnteractor: NYTArticlesnteractor) :
    BaseViewModel<NYTArticlesViewState>() {

    var progress = MutableLiveData<Int>()


    private var getMostViewdArticleLiveData: MutableLiveData<MostviewedArticlesResponse?>

    init {
        getMostViewdArticleLiveData= MutableLiveData()
        progress.postValue(0)
    }

    fun getMostViewedArtices(period: String) {
        progress.postValue(0)
        nytArticlesnteractor.getMostViewedArticles(period)
            .subscribe({
                if(it?.status.equals("OK")){
                    progress.postValue(8)
                    getMostViewdArticleLiveData.value=it


                }

            }, {
                stateLiveData.value = ShowMessageViewState(it.localizedMessage)
            })
    }

    fun getNYTArticlesList():LiveData<MostviewedArticlesResponse?>{
        return getMostViewdArticleLiveData
    }

    fun showMessage(message: String) {
        stateLiveData.value = ShowMessageViewState(message)
    }


}