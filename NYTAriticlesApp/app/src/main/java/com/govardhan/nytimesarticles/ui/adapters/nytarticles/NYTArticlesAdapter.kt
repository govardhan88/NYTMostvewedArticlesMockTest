package com.govardhan.nytimesarticles.ui.adapters.nytarticles

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.govardhan.nytimesarticles.common.utils.getImageUrlFromArticle
import com.govardhan.nytimesarticles.data.model.NYTArticleModel
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem
import com.govardhan.nytimesarticles.databinding.NytarticleListItemBinding

class NYTArticlesAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mNYTArticleItemsList = ArrayList<NYTArticleItem?>()

    fun setAppList(model: List<NYTArticleItem?>) {
        mNYTArticleItemsList.clear()
        mNYTArticleItemsList.addAll(model)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mNYTArticleItemsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val appInfo = mNYTArticleItemsList[position]
        (holder as NYTArticlesAdapter.RecyclerHolderCatIcon).bind(appInfo!!)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding = NytarticleListItemBinding.inflate(layoutInflater, parent, false)
        return RecyclerHolderCatIcon(applicationBinding)
    }

    inner class RecyclerHolderCatIcon(private var nytarticleListItemBinding: NytarticleListItemBinding) :
        RecyclerView.ViewHolder(nytarticleListItemBinding.root) {

        fun bind(nytArticleItem: NYTArticleItem) {
            nytArticleItem?.let {
                val nytArticleModel: NYTArticleModel = NYTArticleModel(it.title, it.byline, it.source, it.publishedDate)
                nytArticleModel.imageUrl = getImageUrlFromArticle(it,"Standard Thumbnail")
                nytArticleModel.nytArticleItem=it
                nytarticleListItemBinding.nytArticleModel = nytArticleModel
            }


        }


    }
}