package com.example.gitappcompose.ui.components

import android.content.Context
import com.example.gitappcompose.R
import com.example.gitappcompose.data.util.Result

fun observeSetFavourite(context: Context, result: Result<Long>, setFavouriteAction: () -> Unit) {
    when(result){
        is Result.Success -> {
            if(result.data == -1L) showToast(context, R.string.favourite_save_conflict_message)
            else showToast(context, R.string.favourite_save_success_message)

            setFavouriteAction.invoke()
        }
        is Result.Error -> { showToast(context, result.errorMessage.orEmpty()) }
        else -> {}
    }
}

fun observeDeleteFavourite(context: Context, result: Result<Int>, deletedAction: () -> Unit) {
    when(result){
        is Result.Success -> {
            deletedAction.invoke()
            showToast(context, R.string.favourite_delete_success_message)
        }
        is Result.Error -> { showToast(context, result.errorMessage.orEmpty()) }
        else -> {}
    }
}