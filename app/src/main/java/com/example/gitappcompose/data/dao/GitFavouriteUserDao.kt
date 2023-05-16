package com.example.gitappcompose.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gitappcompose.data.entities.GitFavouriteUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GitFavouriteUserDao {

    @Query("SELECT * FROM git_favourite_user WHERE favourite = 1")
    fun getAllFavouriteUser(): Flow<List<GitFavouriteUserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUserAsFavourite(user: GitFavouriteUserEntity): Long

    @Delete
    suspend fun deleteFavouriteUser(user: GitFavouriteUserEntity): Int
}