package com.docotel.ikhsansyahrizal.doconewss.helper

import androidx.recyclerview.widget.DiffUtil
import com.docotel.ikhsansyahrizal.first.networking.res.ArticlesItem

val DIFFUTIL_ARTICLEITEM_CALLBACK = object: DiffUtil.ItemCallback<ArticlesItem>(){
    override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
        return oldItem == newItem
    }
}