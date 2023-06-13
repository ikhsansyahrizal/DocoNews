package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import androidx.lifecycle.ViewModel
import com.docotel.ikhsansyahrizal.doconewss.local.bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem


class DetailViewModel(private val bookmarkManager: BookmarkManager): ViewModel() {

    fun addBookmark(article: ArticlesItem) {
        bookmarkManager.addBookmark(article)
    }

    fun removeBookmark(article: ArticlesItem) {
        bookmarkManager.removeBookmark(article)
    }

    fun getBookmarkedArticles(): List<ArticlesItem> {
        return bookmarkManager.getBookmarkedArticles()!!
    }

}
