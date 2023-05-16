package com.example.gitappcompose.di

import com.example.gitappcompose.data.service.GitUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideGitUserService(retrofit: Retrofit): GitUserService =
        retrofit.create(GitUserService::class.java)
}