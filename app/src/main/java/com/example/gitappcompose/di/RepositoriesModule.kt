package com.example.gitappcompose.di

import com.example.gitappcompose.data.repository.GitFavouriteUserRepository
import com.example.gitappcompose.data.repository.GitUserRepository
import com.example.gitappcompose.domain.repository.GitFavouriteUserRepositoryImpl
import com.example.gitappcompose.domain.repository.GitUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindGitUserRepository(repositoryImpl: GitUserRepositoryImpl): GitUserRepository

    @Binds
    fun bindGitFavouriteUserRepository(repositoryImpl: GitFavouriteUserRepositoryImpl): GitFavouriteUserRepository
}