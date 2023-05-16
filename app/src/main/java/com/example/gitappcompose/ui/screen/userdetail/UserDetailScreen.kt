package com.example.gitappcompose.ui.screen.userdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.gitappcompose.R
import com.example.gitappcompose.ui.components.LoadingProgress
import com.example.gitappcompose.ui.components.MessageView
import com.example.gitappcompose.ui.model.UiState
import com.example.gitappcompose.ui.theme.GitAppComposeTheme
import com.example.gitappcompose.ui.util.ObserveLifecycle
import com.example.gitappcompose.ui.util.rememberLifecycleEvent

@Composable
fun UserDetailScreen(
    userName: String,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    viewModel.ObserveLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
    viewModel.username = userName

    val lifecycleEvent = rememberLifecycleEvent()

    LaunchedEffect(key1 = Unit) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) viewModel.getUserData(userName = userName)
    }

    when (val state = viewModel.userDataState.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> { LoadingProgress() }
        is UiState.Success -> {
            UserDetail(
                fullName = state.data.name,
                location = state.data.location,
                company = state.data.company,
                avatarUrl = state.data.avatarUrl,
                repositories = state.data.publicRepos,
                followers = state.data.followers,
                following = state.data.following
            )
        }
        is UiState.Error -> { MessageView(message = state.errorMessage) }
    }
}

@Composable
fun UserDetail(
    fullName: String,
    location: String,
    company: String,
    avatarUrl: String,
    repositories: Int,
    followers: Int,
    following: Int,
    modifier: Modifier = Modifier
) {
    val nameValue by rememberSaveable { mutableStateOf(fullName) }
    val locationValue by rememberSaveable { mutableStateOf(location) }
    val companyValue by rememberSaveable { mutableStateOf(company) }
    val avatarUrlValue by rememberSaveable { mutableStateOf(avatarUrl) }

    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = avatarUrlValue,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .clip(CircleShape)
        )
        Text(
            text = nameValue,
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Text(
            text = locationValue,
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = companyValue,
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
        StatsContent(repositories = repositories, followers = followers, following = following)
    }
}

@Composable
fun StatsContent(
    repositories: Int,
    followers: Int,
    following: Int,
    modifier: Modifier = Modifier
) {
    val repoValue by rememberSaveable { mutableStateOf(repositories.toString()) }
    val followerValue by rememberSaveable { mutableStateOf(followers.toString()) }
    val followingValue by rememberSaveable { mutableStateOf(following.toString()) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.user_detail_repo),
                modifier = modifier
                    .padding(2.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = stringResource(id = R.string.user_detail_follower),
                modifier = modifier
                    .padding(2.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = stringResource(id = R.string.user_detail_following),
                modifier = modifier
                    .padding(2.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = repoValue,
                modifier = modifier
                    .padding(2.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = followerValue,
                modifier = modifier
                    .padding(2.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = followingValue,
                modifier = modifier
                    .padding(2.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun UserDetailPreview() {
    GitAppComposeTheme {
        UserDetail(
            fullName = "Fredyka Maulana",
            location = "Depok",
            company = "Telkom Indonesia",
            avatarUrl = "",
            repositories = 12,
            followers = 2,
            following = 1
        )
    }
}