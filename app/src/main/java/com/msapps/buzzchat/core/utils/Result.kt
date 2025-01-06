package com.msapps.buzzchat.core.utils

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Failure<T>(val error: Throwable): Result<T>()
}