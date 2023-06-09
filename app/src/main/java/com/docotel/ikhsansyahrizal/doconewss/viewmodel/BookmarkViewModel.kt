package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.docotel.ikhsansyahrizal.doconewss.Bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

class BookmarkViewModel(context: Context): ViewModel() {

    private val bookmarkManager: BookmarkManager = BookmarkManager(context)

    private val bookMarkedArticles: MutableLiveData<List<ArticlesItem>> = MutableLiveData()

    init {
        bookMarkedArticles.value = getBookmarkedArticles()
    }

    val bookmarkedArticlesObserve: LiveData<List<ArticlesItem>> get() = bookMarkedArticles


    fun clearAllData() {
        bookmarkManager.clearAllBookmarks()
    }

    fun getBookmarkedArticles(): List<ArticlesItem> {
        return bookmarkManager.getBookmarkedArticles()
    }



}