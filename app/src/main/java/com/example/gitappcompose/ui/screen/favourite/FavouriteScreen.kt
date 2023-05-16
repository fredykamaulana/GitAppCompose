package com.example.gitappcompose.ui.screen.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gitappcompose.ui.components.LoadingProgress
import com.example.gitappcompose.ui.components.MessageView
import com.example.gitappcompose.ui.components.UserItem
import com.example.gitappcompose.ui.components.observeDeleteFavourite
import com.example.gitappcompose.ui.model.UiState
import com.example.gitappcompose.ui.model.UserListUi
import com.example.gitappcompose.ui.navigation.Screen
import com.example.gitappcompose.ui.util.ObserveLifecycle
import com.example.gitappcompose.ui.util.rememberLifecycleEvent

@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    viewModel.ObserveLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)

    val lifecycleEvent = rememberLifecycleEvent()

    LaunchedEffect(key1 = Unit) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) viewModel.getFavouriteUser()
    }

    val context = LocalContext.current
    val deleteAsFavouriteState = viewModel.deleteAsFavourite.collectAsStateWithLifecycle().value
    LaunchedEffect(key1 = deleteAsFavouriteState) {
        observeDeleteFavourite(context = context, result = deleteAsFavouriteState){
            viewModel.getFavouriteUser()
        }
    }

    when (val state = viewModel.favouriteDataState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> { LoadingProgress() }
        is UiState.Success -> {
            FavouriteUserList(
                data = state.data,
                navigateToDetail = navigateToDetail
            )
        }
        is UiState.Error -> { MessageView(message = state.errorMessage) }
    }

}

@Composable
fun FavouriteUserList(
    data: List<UserListUi>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val favouriteUserList by rememberSaveable { mutableStateOf(data) }

    Box(modifier = modifier) {
        LazyColumn {
            items(items = favouriteUserList, key = { it.userId }) {
                UserItem(
                    userImageUrl = it.userImageUrl,
                    userName = it.userName,
                    userId = it.userId,
                    modifier = Modifier.fillMaxWidth(),
                    navigateToDetail = navigateToDetail,
                    isUseMenu = true,
                    screen = Screen.FavouriteUser.route
                )
            }
        }
    }
}