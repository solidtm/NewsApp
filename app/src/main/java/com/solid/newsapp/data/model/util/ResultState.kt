package com.solid.newsapp.data.model.util

sealed class ResultState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResultState<T>()
    class Loading<T>(data: T? = null) : ResultState<T>(data)
    class Error<T>(message: String, data: T? = null) : ResultState<T>(data, message)
}