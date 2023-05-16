package com.example.gitappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gitappcompose.ui.components.BottomBar
import com.example.gitappcompose.ui.components.TopBar
import com.example.gitappcompose.ui.navigation.Screen
import com.example.gitappcompose.ui.screen.about.AboutScreen
import com.example.gitappcompose.ui.screen.favourite.FavouriteScreen
import com.example.gitappcompose.ui.screen.userdetail.UserDetailScreen
import com.example.gitappcompose.ui.screen.userlist.UserListScreen
import com.example.gitappcompose.ui.theme.GitAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GitApp()
                }
            }
        }
    }
}

@Composable
fun GitApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val titlePage = when (currentRoute) {
        Screen.UserList.route -> stringResource(id = R.string.title_bar_user_list)
        Screen.About.route -> stringResource(id = R.string.title_bar_about)
        Screen.UserDetail.route -> stringResource(id = R.string.title_bar_user_detail)
        Screen.FavouriteUser.route -> stringResource(id = R.string.title_bar_favourite)
        else -> stringResource(id = R.string.title_bar_default)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = { Text(text = titlePage) },
                navigateUp = { navController.navigateUp() },
                actions = {
                    if (currentRoute == Screen.UserList.route) {
                        run {
                            IconButton(onClick = {
                                navController.navigate(Screen.FavouriteUser.route)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = stringResource(id = R.string.menu_favourite)
                                )
                            }
                        }
                    } else {
                        run { }
                    }
                }
            )
        },
        bottomBar = {
            if (currentRoute == Screen.UserList.route || currentRoute == Screen.About.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.UserList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.UserList.route) {
                UserListScreen(
                    navigateToDetail = { userName ->
                        navController.navigate(Screen.UserDetail.createRoute(userName = userName))
                    }
                )
            }
            composable(route = Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.UserDetail.route,
                arguments = listOf(navArgument(Screen.UserDetail.userNameArg) {
                    type = NavType.StringType
                })
            ) {
                val userName = it.arguments?.getString(Screen.UserDetail.userNameArg).orEmpty()
                UserDetailScreen(userName = userName)
            }
            composable(route = Screen.FavouriteUser.route) {
                FavouriteScreen(
                    navigateToDetail = { userName ->
                        navController.navigate(Screen.UserDetail.createRoute(userName = userName))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GitAppComposeTheme {
        GitApp()
    }
}