package com.example.gitappcompose.di

import com.example.gitappcompose.data.dao.GitFavouriteUserDao
import com.example.gitappcompose.data.database.GitAppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideFavouriteUserDao(githubUserDb: GitAppDb): GitFavouriteUserDao {
        return githubUserDb.gitFavouriteUserDao()
    }
}