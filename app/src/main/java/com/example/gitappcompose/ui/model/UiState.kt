package com.example.gitappcompose.ui.model

sealed class UiState<out T: Any?> {
    object Loading : UiState<Nothing>() {
        override fun toString(): String = "Loading"
    }

    data class Success<out T: Any>(val data: T) : UiState<T>()
    data class Error(val errorCode: Int, val errorMessage: String, val status: String) :
        UiState<Nothing>()
}