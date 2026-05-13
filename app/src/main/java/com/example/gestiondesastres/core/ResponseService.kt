package com.example.gestiondesastres.core

sealed class ResponseService<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : ResponseService<T>()
    class Success<T>(data: T) : ResponseService<T>(data)
    class Error<T>(message: String) : ResponseService<T>(null, message)
}