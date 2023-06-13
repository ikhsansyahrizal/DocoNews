package com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.docotel.ikhsansyahrizal.doconewss.local.bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.BookmarkViewModel

class BookmarkViewModelFactory(private val bookmarkManager: BookmarkManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(bookmarkManager) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}