package com.example.data.details.source.remote

import com.example.domainlayer.details.UserDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RemoteUserDetailsDataSource @Inject constructor(private val service: IUserDetailsRemoteService) :
    IRemoteUserDetailsDataSource {
    override suspend fun getUserDetails(id: String): Flow<UserDetails> =
        callbackFlow {
            offer(
                service.getUserDetails(id)
            )
            awaitClose { close() }
        }
}