package com.example.domainlayer.details

import com.example.domainlayer.common.Result
import kotlinx.coroutines.flow.Flow

interface IUserDetailsRepository {
    suspend fun getUserDetails(id:String): Flow<Result<UserDetails>>
}