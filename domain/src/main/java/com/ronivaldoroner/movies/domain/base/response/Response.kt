package com.ronivaldoroner.movies.domain.base.response

sealed class Response<out T> {
    data class Success<out T>(val value: T) : Response<T>()
    data class Failure(val throwable: Throwable) : Response<Nothing>()
}