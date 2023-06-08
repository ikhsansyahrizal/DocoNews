package com.docotel.ikhsansyahrizal.first.networking.response

import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.docotel.ikhsansyahrizal.doconewss.networking.retrofit.NewsApiService
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class NewsApiResponse(

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String,

)