package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.docotel.ikhsansyahrizal.doconewss.helper.NetworkAttribute
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.docotel.ikhsansyahrizal.doconewss.networking.retrofit.ApiException
import com.docotel.ikhsansyahrizal.doconewss.networking.retrofit.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {

    private val apiService = ApiService

    private var isLastPage = false
    var isLoading = MutableLiveData<Boolean>()
    var errorLimit = MutableLiveData<Boolean>()

    private val _newsData = MutableLiveData<List<ArticlesItem>>()
    val newsData: LiveData<List<ArticlesItem>> = _newsData

    fun loadNextPage(query: String, currentPage: Int) {
        if (!isLastPage) {
            isLoading.value = true
            viewModelScope.launch {

                try {
                    delay(500)
                    val response = if (query.isNullOrEmpty()) {
                        apiService.getNews(
                            "us",
                            query,
                            currentPage,
                            NetworkAttribute.totalItem,
                            NetworkAttribute.apiKey
                        )
                    } else {
                        apiService.searchNews(
                            query,
                            currentPage,
                            NetworkAttribute.totalItem,
                            NetworkAttribute.apiKey
                        )
                    }

                    if (response.isSuccessful) {
                        val newsList = response.body()?.articles ?: emptyList()
                        _newsData.value = newsList
                    } else {
                        errorLimit.value = true
                        isLoading.value = false
                    }
                } catch (_: ApiException) {

                }
                isLoading.value = false
            }

        }
    }
}

