package com.oyelabs.marvel.universe.utils

sealed class State<T>(val data :T?=null,val message:String?=null){
    class Loading<T>(data:T?=null):State<T>(data)
    class Success<T>(data:T):State<T>(data)
    class Error<T>(message:String,data:T?=null):State<T>(data,message)
}