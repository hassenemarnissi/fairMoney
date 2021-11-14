package com.example.domainlayer.users

import com.example.domainlayer.common.Result
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    suspend fun getUsers(page: Int, limit: Int): Flow<Result<List<User>>>
}