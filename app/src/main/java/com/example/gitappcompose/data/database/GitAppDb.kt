package com.example.gitappcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gitappcompose.data.dao.GitFavouriteUserDao
import com.example.gitappcompose.data.entities.GitFavouriteUserEntity


const val dbVersion = 1
const val exportScheme = false

@Database(
    entities = [
        GitFavouriteUserEntity::class
    ], version = dbVersion, exportSchema = exportScheme
)

abstract class GitAppDb : RoomDatabase() {
    abstract fun gitFavouriteUserDao(): GitFavouriteUserDao
}