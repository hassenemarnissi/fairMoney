package com.example.fairmoney.di

import android.content.Context
import androidx.room.Room
import com.example.data.details.source.local.IUserDetailsDao
import com.example.data.users.source.local.IUserDao
import com.example.fairmoney.database.AppDataBase
import com.example.fairmoney.database.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(database: AppDataBase): IUserDao = database.usersDao()

    @Provides
    @Singleton
    fun provideUserDetailsDao(database: AppDataBase): IUserDetailsDao = database.userDetailsDao()
}