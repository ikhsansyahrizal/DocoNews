package com.docotel.ikhsansyahrizal.doconewss.networking.retrofit

import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.docotel.ikhsansyahrizal.first.networking.response.NewsApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiService {
    private const val BASE_URL = "https://newsapi.org/v2/"

    val loggingInterceptor =HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client =OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val newsApi : NewsApiService = retrofit.create(NewsApiService::class.java)

    suspend fun getNews(country: String, query: String, page: Int, pageSize: Int, apiKey: String) : Response<NewsApiResponse> {
        return newsApi.getNews(country, query, page, pageSize, apiKey)
    }

    suspend fun SearchNews(query: String, page: Int, pageSize: Int, apiKey: String) : Response<NewsApiResponse> {
        return newsApi.searchNews(query, page, pageSize, apiKey)
    }

    suspend fun getStatus(country: String, apiKey: String) : NewsApiResponse {
        return newsApi.getStatus(country, apiKey)
    }

}

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Response<NewsApiResponse>


    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ) : Response<NewsApiResponse>

    @GET("top-headlines")
    suspend fun getStatus(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsApiResponse

}

class ApiException(message: String) : Exception(message)
