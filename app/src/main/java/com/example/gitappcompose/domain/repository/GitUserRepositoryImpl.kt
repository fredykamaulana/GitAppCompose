package com.example.gitappcompose.domain.repository

import com.example.gitappcompose.data.repository.GitUserRepository
import com.example.gitappcompose.data.resource.remote.GitUserRemoteDataSource
import com.example.gitappcompose.data.response.UserDataDto
import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitUserRepositoryImpl @Inject constructor(
    private val gitUserRemoteDataSource: GitUserRemoteDataSource
) : GitUserRepository {

    override suspend fun getUserList(): Flow<Result<List<UserListDto>>> {
        return gitUserRemoteDataSource.getUserList()
    }

    override suspend fun getUserData(username: String): Flow<Result<UserDataDto>> {
        return gitUserRemoteDataSource.getUserData(username = username)
    }

    override suspend fun getUserFollowers(username: String): Flow<Result<List<UserListDto>>> {
        return gitUserRemoteDataSource.getUserFollowers(username = username)
    }

    override suspend fun getUserFollowing(username: String): Flow<Result<List<UserListDto>>> {
        return gitUserRemoteDataSource.getUserFollowing(username = username)
    }
}