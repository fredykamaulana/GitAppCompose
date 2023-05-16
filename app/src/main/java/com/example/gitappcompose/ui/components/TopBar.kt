package com.example.gitappcompose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gitappcompose.R
import com.example.gitappcompose.ui.theme.GitAppComposeTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit
) {

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = {
                navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.menu_back)
                )
            }
        },
        title = title,
        actions = actions
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TopBarPreview() {
    GitAppComposeTheme {
        TopBar(
            title = {},
            navigateUp = {},
            actions = {}
        )
    }
}