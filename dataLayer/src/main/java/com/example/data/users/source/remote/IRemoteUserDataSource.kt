package com.example.data.users.source.remote

import com.example.domainlayer.users.User
import kotlinx.coroutines.flow.Flow
interface IRemoteUserDataSource {
    suspend fun getUsers(page: Int, limit: Int): Flow<List<User>>
}