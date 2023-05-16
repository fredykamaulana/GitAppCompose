package com.example.gitappcompose.domain.usecase.getuserdata

import com.example.gitappcompose.data.repository.GitUserRepository
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.ui.model.UserDataUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserDataInteractor @Inject constructor(private val repository: GitUserRepository) :
    GetUserDataUseCase {

    override suspend fun getUserData(username: String): Flow<Result<UserDataUi>> {
        return repository.getUserData(username = username).map {
            when (it) {
                is Result.Loading -> { Result.Loading }
                is Result.Empty -> { Result.Empty }
                is Result.Success -> {
                    Result.Success(
                        UserDataUi(
                            login = it.data.login.orEmpty(),
                            company = it.data.company ?: "-",
                            id = it.data.id ?: 0,
                            publicRepos = it.data.publicRepos ?: 0,
                            followers = it.data.followers ?: 0,
                            avatarUrl = it.data.avatarUrl.orEmpty(),
                            following = it.data.following ?: 0,
                            name = it.data.name.orEmpty(),
                            location = it.data.location ?: "-"
                        )
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