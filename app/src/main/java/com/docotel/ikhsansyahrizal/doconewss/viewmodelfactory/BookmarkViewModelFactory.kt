package com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.BookmarkViewModel

class BookmarkViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}