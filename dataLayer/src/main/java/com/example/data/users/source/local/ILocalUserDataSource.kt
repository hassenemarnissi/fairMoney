package com.example.data.users.source.local

import com.example.domainlayer.users.User
import kotlinx.coroutines.flow.Flow


interface ILocalUserDataSource {
    suspend fun saveUser(User: User)
    suspend fun deleteAllUsers()
    suspend fun getUsers(limit: Int, page: Int): Flow<List<User>>
}