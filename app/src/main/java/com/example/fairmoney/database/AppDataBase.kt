package com.example.fairmoney.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.details.source.local.IUserDetailsDao
import com.example.data.details.source.local.LocationEntity
import com.example.data.details.source.local.UserDetailsEntity
import com.example.data.users.source.local.IUserDao
import com.example.data.users.source.local.UserEntity
import com.example.fairmoney.BuildConfig.DATABASE_NAME

@Database(entities = [UserEntity::class, UserDetailsEntity::class, LocationEntity::class],
    version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun usersDao(): IUserDao
    abstract fun userDetailsDao(): IUserDetailsDao
}
internal const val DATABASE_NAME = DATABASE_NAME