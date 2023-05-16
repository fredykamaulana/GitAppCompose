package com.example.gitappcompose.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataUi(
    val login: String,
    val company: String,
    val id: Int,
    val publicRepos: Int,
    val followers: Int,
    val avatarUrl: String,
    val following: Int,
    val name: String,
    val location: String
) : Parcelable