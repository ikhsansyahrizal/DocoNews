package com.docotel.ikhsansyahrizal.doconewss.Bookmark

import android.content.Context
import android.content.SharedPreferences
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.google.gson.Gson

class BookmarkManager(context: Context) {

    private val BOOKMARKS_PREFS = "BookmarksPrefs"
    private val BOOKMARKS_KEY = "bookmarks"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(BOOKMARKS_PREFS, Context.MODE_PRIVATE)
    private val gson: Gson = Gson()

    fun addBookmark(article: ArticlesItem) {
        val bookmarks = getBookmarks().toMutableList()
        val articleJson = gson.toJson(article)
        bookmarks.add(articleJson)
        saveBookmarks(bookmarks)
    }

    fun removeBookmark(article: ArticlesItem) {
        val bookmarks = getBookmarks().toMutableList()
        val articleJson = gson.toJson(article)
        bookmarks.remove(articleJson)
        saveBookmarks(bookmarks)
    }

    fun isBookmarked(article: ArticlesItem): Boolean {
        val bookmarks = getBookmarks()
        val articleJson = gson.toJson(article)
        return bookmarks.contains(articleJson)
    }

    private fun getBookmarks(): List<String> {
        val bookmarksJson = sharedPreferences.getStringSet(BOOKMARKS_KEY, null)
        return bookmarksJson?.toList() ?: emptyList()
    }

    private fun saveBookmarks(bookmarks: List<String>) {
        sharedPreferences.edit().putStringSet(BOOKMARKS_KEY, bookmarks.toSet()).apply()
    }

}