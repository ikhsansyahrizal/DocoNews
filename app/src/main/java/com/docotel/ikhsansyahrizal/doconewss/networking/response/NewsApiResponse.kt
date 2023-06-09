package com.docotel.ikhsansyahrizal.first.networking.response

import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.google.gson.annotations.SerializedName

data class NewsApiResponse(

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String,

)