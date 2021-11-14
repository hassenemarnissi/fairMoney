package com.example.domainlayer.common

sealed class Result <out T> {
    object Loading: Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}