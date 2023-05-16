package com.example.gitappcompose.domain.usecase.getuserfollowers

import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow

interface GetUserFollowersUseCase {

    suspend fun getUserFollowers(username: String): Flow<Result<List<UserListDto>>>
}