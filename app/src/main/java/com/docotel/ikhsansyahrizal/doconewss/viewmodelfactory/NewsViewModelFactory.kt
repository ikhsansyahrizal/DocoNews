package com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.docotel.ikhsansyahrizal.doconewss.networking.retrofit.ApiService
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.NewsViewModel

class NewsViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}