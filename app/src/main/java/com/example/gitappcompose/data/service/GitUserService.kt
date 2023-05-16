package com.example.gitappcompose.data.service

import com.example.gitappcompose.data.response.UserDataDto
import com.example.gitappcompose.data.response.UserListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GitUserService {

    @GET("/users")
    suspend fun getUserList(): List<UserListDto>

    @GET("/users/{username}")
    suspend fun getUserData(
        @Path("username") username: String
    ): UserDataDto

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String
    ): List<UserListDto>

    @GET("/users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") userName: String
    ): List<UserListDto>

}