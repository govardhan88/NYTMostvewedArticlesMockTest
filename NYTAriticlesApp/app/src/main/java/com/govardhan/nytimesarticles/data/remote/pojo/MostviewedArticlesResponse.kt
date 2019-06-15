package com.govardhan.nytimesarticles.data.remote.pojo

import com.google.gson.annotations.SerializedName


data class MostviewedArticlesResponse(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("results")
	val results: List<NYTArticleItem?>? = null,

	@field:SerializedName("num_results")
	val numResults: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)