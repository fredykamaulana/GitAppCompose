package com.example.gitappcompose.domain.usecase.getuserfollowers

import com.example.gitappcompose.data.repository.GitUserRepository
import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserFollowersInteractor @Inject constructor(private val repository: GitUserRepository) :
    GetUserFollowersUseCase {

    override suspend fun getUserFollowers(username: String): Flow<Result<List<UserListDto>>> {
        return repository.getUserFollowers(username = username)
    }
}