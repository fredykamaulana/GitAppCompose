package com.example.gitappcompose.data.repository

import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow

interface GitFavouriteUserRepository {

    fun getAllFavouriteUser(): Flow<Result<List<GitFavouriteUserEntity>>>

    suspend fun setUserAsFavourite(user: GitFavouriteUserEntity): Flow<Result<Long>>

    suspend fun deleteFavouriteUser(user: GitFavouriteUserEntity): Flow<Result<Int>>
}