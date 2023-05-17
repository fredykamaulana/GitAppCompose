package com.example.gitappcompose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gitappcompose.ui.theme.GitAppComposeTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit
) {

    TopAppBar(
        modifier = modifier,
        navigationIcon = navigationIcon,
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
            navigationIcon = {},
            actions = {}
        )
    }
}