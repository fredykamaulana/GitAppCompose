package com.example.gitappcompose.ui.screen.userdetail

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.domain.usecase.getuserdata.GetUserDataUseCase
import com.example.gitappcompose.ui.model.UiState
import com.example.gitappcompose.ui.model.UserDataUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel(), DefaultLifecycleObserver {

    private var _userDataState = MutableStateFlow<UiState<UserDataUi>>(UiState.Loading)
    val userDataState = _userDataState.asStateFlow()

    var username = ""

    fun getUserData(userName: String) {

        viewModelScope.launch {
            getUserDataUseCase.getUserData(username = userName).collect {
                when (it) {
                    is Result.Loading -> { _userDataState.value = UiState.Loading }
                    is Result.Empty -> {
                        _userDataState.value = UiState.Error(
                            errorCode = 404, errorMessage = "Data is Empty", status = "Empty"
                        )
                    }
                    is Result.Success -> { _userDataState.value = UiState.Success(data = it.data) }
                    is Result.Error -> {
                        _userDataState.value = UiState.Error(
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
        getUserData(userName = username)
    }
}