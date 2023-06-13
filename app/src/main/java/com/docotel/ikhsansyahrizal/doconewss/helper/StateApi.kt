package com.docotel.ikhsansyahrizal.doconewss.helper

sealed class StateApi<out T> {

    object Loading: StateApi<Nothing>()

    data class Success<out T>(val data:T):StateApi<T>()

    data class Error(val message:String):StateApi<Nothing>()

    object NotLoading:StateApi<Nothing>()

}
