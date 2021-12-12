package com.example.data.details.source.local

import com.example.data.details.source.toUserDetails
import com.example.data.details.source.toUserDetailsEntity
import com.example.domainlayer.details.UserDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalUserDetailsDataSource  @Inject constructor(private val dao: IUserDetailsDao):ILocalUserDetailsDataSource {

    override suspend fun saveUserDetails(userDetails: UserDetails) =
        dao.saveUserDetails(userDetails.toUserDetailsEntity())


    override suspend fun deleteUserDetails(id: String) =
        dao.deleteUserDetails(id)

    override suspend fun getUserDetails(id: String): Flow<UserDetails> =
        callbackFlow {
            dao.getUserDetails(id).toUserDetails().let { offer(it) }
            awaitClose { cancel() }
        }

    override suspend fun deleteAllUserDetails() {
        dao.deleteAllUserDetails()
        dao.deleteAllLocation()
    }

}