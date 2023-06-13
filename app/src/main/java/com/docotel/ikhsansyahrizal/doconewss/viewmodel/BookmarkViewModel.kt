package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.docotel.ikhsansyahrizal.doconewss.local.bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

class BookmarkViewModel(private val bookmarkManager: BookmarkManager): ViewModel() {

    private val _bookMarkedArticles: MutableLiveData<List<ArticlesItem>> = MutableLiveData()
    val bookmarkedArticlesObserve: LiveData<List<ArticlesItem>> get() = _bookMarkedArticles


    init {
        _bookMarkedArticles.value = getBookmarkedArticles()
    }



    fun clearAllData() {
        bookmarkManager.clearAllBookmarks()
    }

    fun getBookmarkedArticles(): List<ArticlesItem> {
        return bookmarkManager.getBookmarkedArticles()!!
    }



}