package com.example.gitappcompose.domain.usecase.getuserfollowing

import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow

interface GetUserFollowingUseCase {

    suspend fun getUserFollowing(username: String): Flow<Result<List<UserListDto>>>
}