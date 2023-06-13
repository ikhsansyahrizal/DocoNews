package com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.docotel.ikhsansyahrizal.doconewss.local.bookmark.BookmarkManager
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.DetailViewModel

class DetailViewModelFactory(private val bookmarkManager: BookmarkManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(bookmarkManager) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}