package com.example.gitappcompose.domain.usecase.getuserfollowing

import com.example.gitappcompose.data.repository.GitUserRepository
import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserFollowingInteractor @Inject constructor(private val repository: GitUserRepository) :
    GetUserFollowingUseCase {

    override suspend fun getUserFollowing(username: String): Flow<Result<List<UserListDto>>> {
        return repository.getUserFollowing(username = username)
    }
}