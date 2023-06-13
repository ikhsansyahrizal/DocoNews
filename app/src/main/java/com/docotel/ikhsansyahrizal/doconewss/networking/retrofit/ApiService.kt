package com.docotel.ikhsansyahrizal.doconewss.networking.retrofit

import com.docotel.ikhsansyahrizal.doconewss.helper.NetworkAttribute
import com.docotel.ikhsansyahrizal.first.networking.response.NewsApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiService {

    private val loggingInterceptor =HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client =OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkAttribute.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val newsApi : NewsApiService = retrofit.create(NewsApiService::class.java)

    suspend fun getNews(country: String, query: String, page: Int, pageSize: Int, apiKey: String) : Response<NewsApiResponse> {
        return newsApi.getNews(country, query, page, pageSize, apiKey)
    }

    suspend fun searchNews(query: String, page: Int, pageSize: Int, apiKey: String) : Response<NewsApiResponse> {
        return newsApi.searchNews(query, page, pageSize, apiKey)
    }

}

interface NewsApiService {
    @GET(NetworkAttribute.TOP_HEADLINE)
    suspend fun getNews(
        @Query("country") country: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Response<NewsApiResponse>


    @GET(NetworkAttribute.EVERYTHING)
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ) : Response<NewsApiResponse>

}