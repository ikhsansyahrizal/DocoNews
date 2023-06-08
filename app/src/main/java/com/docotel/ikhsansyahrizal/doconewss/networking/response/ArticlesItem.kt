package com.docotel.ikhsansyahrizal.first.networking.res

import com.docotel.ikhsansyahrizal.first.networking.response.Source
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticlesItem(

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("urlToImage")
	val urlToImage: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("source")
	val source: Source,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("publishedAt")
	val publishedAt: String,

	@field:SerializedName("content")
	val content: String
) : Serializable