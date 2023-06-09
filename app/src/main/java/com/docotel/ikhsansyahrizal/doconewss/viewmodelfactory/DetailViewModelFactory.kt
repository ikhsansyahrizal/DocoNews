package com.docotel.ikhsansyahrizal.doconewss.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.DetailViewModel

class DetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}