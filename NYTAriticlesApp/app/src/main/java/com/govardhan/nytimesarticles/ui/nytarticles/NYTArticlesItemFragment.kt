package com.govardhan.nytimesarticles.ui.nytarticles


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.gson.Gson
import com.govardhan.nytimesarticles.R
import com.govardhan.nytimesarticles.common.NYT_ARTICLE_ARG_KEY
import com.govardhan.nytimesarticles.common.events.RxBus
import com.govardhan.nytimesarticles.common.utils.WebviewObserver
import com.govardhan.nytimesarticles.common.utils.getImageUrlFromArticle
import com.govardhan.nytimesarticles.common.utils.getDataFromUnknownType
import com.govardhan.nytimesarticles.common.utils.showTextDialog
import com.govardhan.nytimesarticles.data.model.NYTArticlesParsedData
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem
import com.govardhan.nytimesarticles.databinding.FragmentNytArticlesDetailsBinding
import com.govardhan.nytimesarticles.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection


class NYTArticlesItemFragment : BaseFragment<NYTArticlesViewState, NYTArticlesListFragmentViewModel>() {

    private lateinit var binding: FragmentNytArticlesDetailsBinding


    override fun getFragmentViewModel(): NYTArticlesListFragmentViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(NYTArticlesListFragmentViewModel::class.java)

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nyt_articles_details, container, false)
        binding.activity=activity as AppCompatActivity
        arguments?.let {
            if (it.containsKey(NYT_ARTICLE_ARG_KEY)) {
                val nytArticleData = it.getString(NYT_ARTICLE_ARG_KEY)
                if (!nytArticleData.isNullOrEmpty()) {
                    val gson = Gson()
                    val nytArticleItem = gson.fromJson<NYTArticleItem>(nytArticleData,NYTArticleItem::class.java)
                    binding.nytArticlesItem=nytArticleItem
                    val nytArticlesParsedData=NYTArticlesParsedData(getImageUrlFromArticle(nytArticleItem,"Jumbo"),"","","","")
                    nytArticleItem.perFacet?.let {
                       nytArticlesParsedData.pesFacet= getDataFromUnknownType(it)
                    }
                    nytArticleItem.orgFacet?.let {
                        nytArticlesParsedData.orgFacet= getDataFromUnknownType(it)
                    }
                    nytArticleItem.desFacet?.let {
                        nytArticlesParsedData.desFacet= getDataFromUnknownType(it)
                    }
                    nytArticleItem.geoFacet?.let {
                        nytArticlesParsedData.geoFacet= getDataFromUnknownType(it)
                    }

                    binding.nytArticleParsed=nytArticlesParsedData

                }
            }
        }
        binding.webViewObserver = WebviewObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initView()
    }

    private fun initView() {
    }

    fun showMessage(message: String, edtText: EditText) = showTextDialog(activity as Context, message) {
        RxBus.publish(RxBus.Event.KeyboardEvent(true, edtText))
    }

    fun showMessage(message: String) = showTextDialog(activity as Context, message)

    override fun render(viewState: NYTArticlesViewState) {
        when (viewState) {
            is ShowMessageViewState -> {
                showMessage(viewState.message)
            }

        }
    }

}