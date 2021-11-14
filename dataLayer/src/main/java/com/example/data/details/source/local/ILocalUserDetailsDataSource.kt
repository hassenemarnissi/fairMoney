package com.example.data.details.source.local

import com.example.domainlayer.entities.UserDetails
import kotlinx.coroutines.flow.Flow

interface ILocalUserDetailsDataSource {
    suspend fun saveUserDetails(userDetails: UserDetails)
    suspend fun deleteUserDetails(id:String)
    suspend fun getUserDetails(id:String): Flow<UserDetails>
    suspend fun deleteAllUserDetails()
}