package com.example.gitappcompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.gitappcompose.R
import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import com.example.gitappcompose.ui.navigation.Screen
import com.example.gitappcompose.ui.screen.favourite.FavouriteViewModel
import com.example.gitappcompose.ui.theme.GitAppComposeTheme

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    userImageUrl: String,
    userName: String,
    userId: Int,
    navigateToDetail: (String) -> Unit,
    isUseMenu: Boolean,
    screen: String
) {
    val urlImage by rememberSaveable { mutableStateOf(userImageUrl) }
    val name by rememberSaveable { mutableStateOf(userName) }
    val id by rememberSaveable { mutableStateOf(userId) }
    val isUseMenuValue by rememberSaveable { mutableStateOf(isUseMenu) }
    val screenValue by rememberSaveable { mutableStateOf(screen) }

    val viewModel: FavouriteViewModel = hiltViewModel()
    val userData = GitFavouriteUserEntity(
        login = name,
        id = id,
        avatarUrl = urlImage,
        name = name
    )
    val menuItems = when (screenValue) {
        Screen.UserList.route -> {
            mapOf(
                stringResource(id = R.string.favourite_save_user) to {
                    viewModel.setAsFavourite(userData.copy(favourite = true))
                }
            )
        }
        Screen.FavouriteUser.route -> {
            mapOf(
                stringResource(id = R.string.favourite_delete_user) to {
                    viewModel.deleteAsFavourite(userData.copy(favourite = false))
                }
            )
        }
        else -> {
            mapOf()
        }
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navigateToDetail(name)
            },
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = urlImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Text(
                modifier = modifier.weight(1f),
                text = name,
                style = MaterialTheme.typography.h1
            )
            if (isUseMenuValue) DropDownMenu(menuItems = menuItems)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserItemPreview() {
    GitAppComposeTheme {
        UserItem(
            userImageUrl = "https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png",
            userName = "User 1",
            userId = 0,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            navigateToDetail = {},
            isUseMenu = true,
            screen = ""
        )
    }
}
