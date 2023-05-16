package com.example.gitappcompose.domain.usecase.getuserlist

import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.ui.model.UserListUi
import kotlinx.coroutines.flow.Flow

interface GetUserListUseCase {

    suspend fun getUserList(): Flow<Result<List<UserListUi>>>
}