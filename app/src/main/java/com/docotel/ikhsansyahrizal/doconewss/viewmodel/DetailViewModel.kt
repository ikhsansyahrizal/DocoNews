package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.docotel.ikhsansyahrizal.doconewss.Bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

class DetailViewModel(context: Context): ViewModel() {

    private val bookmarkManager: BookmarkManager = BookmarkManager(context)


    fun addBookmark(article: ArticlesItem) {
        bookmarkManager.addBookmark(article)
    }

    fun removeBookmark(article: ArticlesItem) {
        bookmarkManager.removeBookmark(article)
    }

    fun getBookmarkedArticles(): List<ArticlesItem> {
        return bookmarkManager.getBookmarkedArticles()
    }



}