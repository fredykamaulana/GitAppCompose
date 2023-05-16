package com.example.gitappcompose.ui.screen.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gitappcompose.R
import com.example.gitappcompose.ui.theme.GitAppComposeTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    profileImageUrl: String = "https://media.licdn.com/dms/image/C5603AQERjdo3nErXpg/profile-displayphoto-shrink_800_800/0/1631106210890?e=1687996800&v=beta&t=YM8cljyj-8V-HQ5tuO7MCw2kauUhjuYKDS7Mu58VsNo"
) {

    val urlImage by rememberSaveable { mutableStateOf(profileImageUrl) }

    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = urlImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = R.string.profile_name),
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Text(
            text = stringResource(id = R.string.profile_email),
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
        Text(
            text = stringResource(id = R.string.profile_role),
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AboutPreview() {
    GitAppComposeTheme {
        AboutScreen()
    }
}