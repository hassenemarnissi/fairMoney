package com.example.data.users.source.remote

import com.example.domainlayer.users.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RemoteUserDataSource @Inject constructor(private val service: IUserRemoteService) :
    IRemoteUserDataSource {
    override suspend fun getUsers(page: Int, limit: Int): Flow<List<User>> =
        callbackFlow {
            offer(
                service.getUsers(page, limit).data
            )
            awaitClose { close() }
        }

}