package com.example.gitappcompose.ui.screen.userlist

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.domain.usecase.getuserlist.GetUserListUseCase
import com.example.gitappcompose.ui.model.UiState
import com.example.gitappcompose.ui.model.UserListUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel(), DefaultLifecycleObserver {

    private var _userListState = MutableStateFlow<UiState<List<UserListUi>>>(UiState.Loading)
    val userListState = _userListState.asStateFlow()

    fun getUserList() {
        viewModelScope.launch {
            getUserListUseCase.getUserList().collect {
                when (it) {
                    is Result.Loading -> { _userListState.value = UiState.Loading }
                    is Result.Empty -> {
                        _userListState.value = UiState.Error(
                            errorCode = 404, errorMessage = "Data is Empty", status = "Empty"
                        )
                    }
                    is Result.Success -> { _userListState.value = UiState.Success(data = it.data) }
                    is Result.Error -> {
                        _userListState.value = UiState.Error(
                            errorCode = it.code ?: 0,
                            errorMessage = it.errorMessage.orEmpty(),
                            status = it.status.orEmpty()
                        )
                    }
                }
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        getUserList()
    }
}