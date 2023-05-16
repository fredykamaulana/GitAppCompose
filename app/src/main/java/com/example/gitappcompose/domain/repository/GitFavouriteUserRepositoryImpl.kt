package com.example.gitappcompose.domain.repository

import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import com.example.gitappcompose.data.repository.GitFavouriteUserRepository
import com.example.gitappcompose.data.resource.local.GitFavouriteUserLocalSource
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitFavouriteUserRepositoryImpl @Inject constructor(
    private val dataSource: GitFavouriteUserLocalSource
) : GitFavouriteUserRepository {

    override fun getAllFavouriteUser(): Flow<Result<List<GitFavouriteUserEntity>>> {
        return dataSource.getAllFavouriteUser()
    }

    override suspend fun setUserAsFavourite(user: GitFavouriteUserEntity): Flow<Result<Long>> {
        return dataSource.setUserAsFavourite(user = user)
    }

    override suspend fun deleteFavouriteUser(user: GitFavouriteUserEntity): Flow<Result<Int>> {
        return dataSource.deleteFavouriteUser(user = user)
    }
}