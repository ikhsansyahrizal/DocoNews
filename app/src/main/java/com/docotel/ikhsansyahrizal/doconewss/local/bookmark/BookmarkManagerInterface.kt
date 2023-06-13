package com.docotel.ikhsansyahrizal.doconewss.local.bookmark

import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

interface BookmarkManagerInterface {

    fun getBookmarkedArticles() : List<ArticlesItem>?

    fun saveBookmarkedArticles(bookmarkedArticles: ArrayList<ArticlesItem>)

    fun addBookmark(article: ArticlesItem)

    fun removeBookmark(article: ArticlesItem)

    fun clearAllBookmarks()
}