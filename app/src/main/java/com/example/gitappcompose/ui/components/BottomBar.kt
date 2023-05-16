package com.example.gitappcompose.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gitappcompose.R
import com.example.gitappcompose.ui.navigation.NavigationItem
import com.example.gitappcompose.ui.navigation.Screen

val navigationItems = listOf(
    NavigationItem(
        menuTitle = "User",
        menuIcon = R.drawable.ic_user_list,
        screen = Screen.UserList
    ),
    NavigationItem(
        menuTitle = "About",
        menuIcon = R.drawable.ic_app_information,
        screen = Screen.About
    )
)

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = it.menuIcon),
                        contentDescription = it.menuTitle
                    )
                },
                selected = currentRoute == it.screen.route,
                unselectedContentColor = Color.LightGray,
                onClick = {
                    navController.navigate(it.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}