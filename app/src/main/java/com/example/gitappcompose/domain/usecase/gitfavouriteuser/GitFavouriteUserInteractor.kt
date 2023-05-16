package com.example.gitappcompose.domain.usecase.gitfavouriteuser

import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import com.example.gitappcompose.data.repository.GitFavouriteUserRepository
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.ui.model.UserListUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GitFavouriteUserInteractor @Inject constructor(
    private val repository: GitFavouriteUserRepository
) : GitFavouriteUserUseCases {
    override fun getAllFavouriteUser(): Flow<Result<List<UserListUi>>> {
        return repository.getAllFavouriteUser().map {
            when (it) {
                is Result.Loading -> { Result.Loading }
                is Result.Empty -> { Result.Empty }
                is Result.Success -> {
                    Result.Success(
                        it.data.map { userData ->
                            UserListUi(
                                userId = userData.id ?: 0,
                                userImageUrl = userData.avatarUrl.orEmpty(),
                                userName = userData.name.orEmpty()
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

    override suspend fun setUserAsFavourite(user: GitFavouriteUserEntity): Flow<Result<Long>> {
        return repository.setUserAsFavourite(user = user)
    }

    override suspend fun deleteFavouriteUser(user: GitFavouriteUserEntity): Flow<Result<Int>> {
        return repository.deleteFavouriteUser(user = user)
    }
}