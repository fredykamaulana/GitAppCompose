package com.example.gitappcompose.domain.usecase.getuserdata

import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.ui.model.UserDataUi
import kotlinx.coroutines.flow.Flow

interface GetUserDataUseCase {

    suspend fun getUserData(username: String): Flow<Result<UserDataUi>>
}