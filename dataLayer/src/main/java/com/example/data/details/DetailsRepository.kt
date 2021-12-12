package com.example.data.details

import com.example.data.details.source.local.LocalUserDetailsDataSource
import com.example.data.details.source.remote.RemoteUserDetailsDataSource
import com.example.domainlayer.common.Result
import com.example.domainlayer.details.IUserDetailsRepository
import com.example.domainlayer.details.UserDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailsRepository @Inject constructor(
    private val localUserDetailsDataSource: LocalUserDetailsDataSource,
    private val remoteUserDetailsDataSource: RemoteUserDetailsDataSource
) : IUserDetailsRepository {
    override suspend fun getUserDetails(id: String): Flow<Result<UserDetails>> = callbackFlow {
        localUserDetailsDataSource.getUserDetails(id).collect { localUserDetail ->
            if (localUserDetail != null) {
                try {
                    remoteUserDetailsDataSource.getUserDetails(id).collect { remoteUserDetails ->
                        if (remoteUserDetails != null) {
                            localUserDetailsDataSource.deleteUserDetails(id)
                            localUserDetailsDataSource.saveUserDetails(remoteUserDetails)
                            offer(Result.Success(remoteUserDetails))
                        }
                    }
                } catch (e: Exception) {
                    offer(Result.Failure(e))
                }
            } else {
                offer(Result.Success(localUserDetail))
            }
        }
        awaitClose { cancel() }
    }

}