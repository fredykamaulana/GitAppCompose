package com.example.gitappcompose.data.resource.local

import com.example.gitappcompose.data.dao.GitFavouriteUserDao
import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.data.util.SafeDbCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GitFavouriteUserLocalSource @Inject constructor(private val dao: GitFavouriteUserDao) :
    SafeDbCall() {

    fun getAllFavouriteUser(): Flow<Result<List<GitFavouriteUserEntity>>> {
        return safeDbCall { dao.getAllFavouriteUser().first() }
    }

    suspend fun setUserAsFavourite(user: GitFavouriteUserEntity): Flow<Result<Long>> {
        return safeDbCall { dao.setUserAsFavourite(user = user) }
    }

    suspend fun deleteFavouriteUser(user: GitFavouriteUserEntity): Flow<Result<Int>> {
        return safeDbCall { dao.deleteFavouriteUser(user = user) }
    }
}