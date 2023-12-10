package com.faradhy.subusersgitapp.databs

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T) : Result<T>()

}