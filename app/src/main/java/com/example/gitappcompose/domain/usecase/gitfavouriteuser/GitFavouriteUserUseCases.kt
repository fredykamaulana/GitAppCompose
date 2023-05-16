package com.example.gitappcompose.domain.usecase.gitfavouriteuser

import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import kotlinx.coroutines.flow.Flow
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.ui.model.UserListUi

interface GitFavouriteUserUseCases {

    fun getAllFavouriteUser(): Flow<Result<List<UserListUi>>>

    suspend fun setUserAsFavourite(user: GitFavouriteUserEntity): Flow<Result<Long>>

    suspend fun deleteFavouriteUser(user: GitFavouriteUserEntity): Flow<Result<Int>>
}