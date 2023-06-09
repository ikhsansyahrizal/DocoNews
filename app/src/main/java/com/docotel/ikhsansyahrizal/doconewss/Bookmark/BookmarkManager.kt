package com.docotel.ikhsansyahrizal.doconewss.Bookmark

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class BookmarkManager(context: Context) {

    private val BOOKMARKS_PREFS = "BookmarksPrefs"
    private val BOOKMARKS_KEY = "bookmarks"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(BOOKMARKS_PREFS, Context.MODE_PRIVATE)
    private val gson: Gson = Gson()


    fun getBookmarkedArticles(): List<ArticlesItem> {
        val articlesStringSet = sharedPreferences.getStringSet(BOOKMARKS_KEY, emptySet()) ?: emptySet()

        val bookmarkedArticles = ArrayList<ArticlesItem>()
        val linkedHashSet = LinkedHashSet<String>(articlesStringSet)

        linkedHashSet.forEach {
            val article = gson.fromJson(it, ArticlesItem::class.java)
            bookmarkedArticles.add(article)
        }

        return bookmarkedArticles.toList()
    }


    private fun saveBookmarkedArticles(bookmarkedArticles: Set<ArticlesItem>) {
        val articlesStringSet = bookmarkedArticles.map { gson.toJson(it) }.toSet()
        sharedPreferences.edit().putStringSet(BOOKMARKS_KEY, articlesStringSet).apply()
    }

    fun addBookmark(article: ArticlesItem) {
        val bookmarkedArticles = getBookmarkedArticles().toMutableSet()
        bookmarkedArticles.add(article)
        saveBookmarkedArticles(bookmarkedArticles)
    }

    fun removeBookmark(article: ArticlesItem) {
        val bookmarkedArticles = getBookmarkedArticles().toMutableSet()
        bookmarkedArticles.remove(article)
        saveBookmarkedArticles(bookmarkedArticles)
    }

   fun clearAllBookmarks() {
       sharedPreferences.edit().remove(BOOKMARKS_KEY).apply()
   }


}