package com.example.gitappcompose.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserListUi(
    val userId: Int,
    val userImageUrl: String,
    val userName: String
) : Parcelable
