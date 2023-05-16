package com.example.gitappcompose.ui.screen.favourite

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import com.example.gitappcompose.data.util.Result
import com.example.gitappcompose.domain.usecase.gitfavouriteuser.GitFavouriteUserUseCases
import com.example.gitappcompose.ui.model.UiState
import com.example.gitappcompose.ui.model.UserListUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val gitFavouriteUserUseCases: GitFavouriteUserUseCases
) : ViewModel(), DefaultLifecycleObserver {

    private var _favouriteUserState = MutableStateFlow<UiState<List<UserListUi>>>(UiState.Loading)
    val favouriteDataState = _favouriteUserState.asStateFlow()

    fun getFavouriteUser() {

        viewModelScope.launch {
            gitFavouriteUserUseCases.getAllFavouriteUser().collect {
                when (it) {
                    is Result.Loading -> {
                        _favouriteUserState.value = UiState.Loading
                    }
                    is Result.Empty -> {
                        _favouriteUserState.value = UiState.Error(
                            errorCode = 404, errorMessage = "Data is Empty", status = "Empty"
                        )
                    }
                    is Result.Success -> {
                        if (it.data.isNotEmpty()){
                            _favouriteUserState.value = UiState.Success(data = it.data)
                        } else _favouriteUserState.value = UiState.Error(
                            errorCode = 404, errorMessage = "Data is Empty", status = "Empty"
                        )
                    }
                    is Result.Error -> {
                        _favouriteUserState.value = UiState.Error(
                            errorCode = it.code ?: 0,
                            errorMessage = it.errorMessage.orEmpty(),
                            status = it.status.orEmpty()
                        )
                    }
                }
            }
        }
    }

    private var _deleteAsFavourite = MutableStateFlow<Result<Int>>(Result.Loading)
    val deleteAsFavourite = _deleteAsFavourite.asStateFlow()

    fun deleteAsFavourite(user: GitFavouriteUserEntity) {
        viewModelScope.launch {
            _deleteAsFavourite.value = gitFavouriteUserUseCases.deleteFavouriteUser(user = user).last()
        }
    }

    private var _setAsFavourite = MutableStateFlow<Result<Long>>(Result.Loading)
    val setAsFavourite = _setAsFavourite.asStateFlow()

    fun setAsFavourite(user: GitFavouriteUserEntity) {
        viewModelScope.launch {
            _setAsFavourite.value = gitFavouriteUserUseCases.setUserAsFavourite(user = user).last()
        }
    }

    fun setAsFavouriteReload() {
        viewModelScope.launch {
            _setAsFavourite.value = Result.Loading
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        getFavouriteUser()
    }
}