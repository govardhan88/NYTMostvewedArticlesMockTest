package com.govardhan.nytimesarticles.data.remote.pojo

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import java.util.*

data class NYTArticleItem(

    @field:SerializedName("per_facet")
    val perFacet: Any? = null,

    @field:SerializedName("org_facet")
    val orgFacet: Any? = null,

    @field:SerializedName("column")
    val column: String? = null,

    @field:SerializedName("section")
    val section: String? = null,

    @field:SerializedName("abstract")
    val memberAbstract: String? = null,

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("asset_id")
    val assetId: Long? = null,

    @field:SerializedName("media")
    val media: List<MediaItem?>? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("des_facet")
    val desFacet: Any? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("adx_keywords")
    val adxKeywords: String? = null,

    @field:SerializedName("geo_facet")
    val geoFacet: Any? = null,

    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("byline")
    val byline: String? = null,

    @field:SerializedName("published_date")
    val publishedDate: String? = null,

    @field:SerializedName("views")
    val views: Int? = null

)