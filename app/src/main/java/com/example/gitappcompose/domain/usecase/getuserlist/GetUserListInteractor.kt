package com.example.gitappcompose.domain.usecase.getuserlist

import com.example.gitappcompose.data.repository.GitUserRepository
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.ui.model.UserListUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserListInteractor @Inject constructor(private val repository: GitUserRepository) :
    GetUserListUseCase {

    override suspend fun getUserList(): Flow<Result<List<UserListUi>>> {
        return repository.getUserList().map {
            when (it) {
                is Result.Loading -> { Result.Loading }
                is Result.Empty -> { Result.Empty }
                is Result.Success -> {
                    Result.Success(
                        it.data.map { userData ->
                            UserListUi(
                                userId = userData.id ?: 0,
                                userImageUrl = userData.avatarUrl.orEmpty(),
                                userName = userData.login.orEmpty()
                            )
                        }
                    )
                }
                is Result.Error -> {
                    Result.Error(
                        cause = it.cause,
                        code = it.code,
                        errorMessage = it.errorMessage,
                        status = it.status
                    )
                }
            }
        }
    }
}