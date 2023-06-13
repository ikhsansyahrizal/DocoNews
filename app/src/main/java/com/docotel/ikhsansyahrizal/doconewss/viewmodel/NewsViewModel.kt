package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.docotel.ikhsansyahrizal.doconewss.helper.NetworkAttribute
import com.docotel.ikhsansyahrizal.doconewss.helper.StateApi
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.docotel.ikhsansyahrizal.doconewss.networking.retrofit.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(private val apiService: ApiService) : ViewModel() {

    private var isLastPage = false

    private val _stateApi : MutableLiveData<StateApi<List<ArticlesItem>>> = MutableLiveData()
    val stateApi: LiveData<StateApi<List<ArticlesItem>>> = _stateApi

    fun loadNextPage(query: String, currentPage: Int) {
        if (!isLastPage) {
            _stateApi.value = StateApi.Loading
            viewModelScope.launch {
                try {
                    val response = if (query.isEmpty()) {
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
                        _stateApi.value = StateApi.Success(newsList)
                    } else {
                        _stateApi.value = StateApi.Error(NetworkAttribute.errorMessage)
                        _stateApi.value = StateApi.NotLoading
                    }
                } catch (e: Exception) {
                    print(e)
                }
                _stateApi.value = StateApi.NotLoading
            }

        }
    }
}

