package com.example.gitappcompose.data.repository

import com.example.gitappcompose.data.response.UserDataDto
import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow

interface GitUserRepository {

    suspend fun getUserList(): Flow<Result<List<UserListDto>>>

    suspend fun getUserData(username: String): Flow<Result<UserDataDto>>

    suspend fun getUserFollowers(username: String): Flow<Result<List<UserListDto>>>

    suspend fun getUserFollowing(username: String): Flow<Result<List<UserListDto>>>
}