package com.example.gitappcompose.data.resource.remote

import com.example.gitappcompose.data.response.UserDataDto
import com.example.gitappcompose.data.response.UserListDto
import com.example.gitappcompose.data.service.GitUserService
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.data.util.SafeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitUserRemoteDataSource @Inject constructor(
    private val gitUserService: GitUserService
) : SafeApiCall() {

    fun getUserList(): Flow<Result<List<UserListDto>>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { gitUserService.getUserList() })
        }
    }

    fun getUserData(username: String): Flow<Result<UserDataDto>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { gitUserService.getUserData(username = username) })
        }
    }

    fun getUserFollowers(username: String): Flow<Result<List<UserListDto>>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { gitUserService.getUserFollowers(username) })
        }
    }

    fun getUserFollowing(username: String): Flow<Result<List<UserListDto>>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { gitUserService.getUserFollowing(username) })
        }
    }
}