package com.example.data.details.source.remote

import com.example.domainlayer.details.UserDetails
import kotlinx.coroutines.flow.Flow

interface IRemoteUserDetailsDataSource {
    suspend fun getUserDetails(id:String): Flow<UserDetails>
}