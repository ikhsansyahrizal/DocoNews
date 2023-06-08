package com.docotel.ikhsansyahrizal.first.networking.res

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesItem(

	@field:SerializedName("author")
	val author: String?,

	@field:SerializedName("urlToImage")
	val urlToImage: String?,

	@field:SerializedName("description")
	val description: String?,

//	@field:SerializedName("source")
//	val source: String,

	@field:SerializedName("title")
	val title: String?,

	@field:SerializedName("url")
	val url: String?,

	@field:SerializedName("publishedAt")
	val publishedAt: String?,

	@field:SerializedName("content")
	val content: String?
) : Parcelable