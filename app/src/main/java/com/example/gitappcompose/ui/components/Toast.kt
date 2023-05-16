package com.example.gitappcompose.ui.components

import android.content.Context
import android.widget.Toast

fun showToast(
    context: Context,
    stringResource: Int
) {
    Toast.makeText(context, context.getString(stringResource), Toast.LENGTH_SHORT).show()
}

fun showToast(
    context: Context,
    message: String
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}