package com.example.gitappcompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gitappcompose.ui.theme.GitAppComposeTheme

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    menuItems: Map<String, () -> Unit>,
) {
    var expanded by remember { mutableStateOf(false) }
    val menuItemsValue by remember { mutableStateOf(menuItems) }

    Box(
        modifier = modifier.wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "User Action Menu")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuItemsValue.forEach {
                DropdownMenuItem(
                    onClick = {
                        it.value.invoke()
                        expanded = false
                    }
                ) {
                    Text(it.key)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropDownMenu() {
    GitAppComposeTheme {
        DropDownMenu(
            menuItems = mapOf()
        )
    }
}