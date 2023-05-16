package com.example.gitappcompose.ui.navigation

sealed class Screen(val route: String) {
    object UserList : Screen(route = "user/list")
    object About : Screen(route = "about")
    object UserDetail: Screen(route = "user/{userName}"){
        fun createRoute(userName: String) = "user/$userName"
        const val userNameArg = "userName"
    }
    object FavouriteUser: Screen(route = "user/favourite")
}
