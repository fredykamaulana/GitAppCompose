package com.example.gitappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.gitappcompose.data.database.GitAppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideGithubUserDatabase(@ApplicationContext appContext: Context): GitAppDb {
        return Room.databaseBuilder(
            appContext,
            GitAppDb::class.java,
            "GithubUserDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }
}