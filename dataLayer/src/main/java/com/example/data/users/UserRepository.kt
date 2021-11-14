package com.example.data.users

import com.example.data.users.source.local.LocalUserDataSource
import com.example.data.users.source.remote.RemoteUserDataSource
import com.example.domainlayer.users.User
import com.example.domainlayer.common.Result
import com.example.domainlayer.users.IUsersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserRepository @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDataSource: RemoteUserDataSource
) : IUsersRepository {


    override suspend fun getUsers(page: Int, limit: Int): Flow<Result<List<User>>>
        = callbackFlow {
            localUserDataSource.getUsers(page, limit).collect { localUserList ->
                if (localUserList.isEmpty()) {
                    try {
                        remoteUserDataSource.getUsers(page, limit).collect { remoteUserList ->
                            if (remoteUserList.isNotEmpty()) {
                                localUserDataSource.deleteAllUsers()
                                remoteUserList.forEach {
                                    localUserDataSource.saveUser(it)
                                }
                                offer(Result.Success(remoteUserList))
                            }
                        }
                    } catch (e: Exception) {
                        offer(Result.Failure(e))
                    }
                } else {
                    offer(Result.Success(localUserList))
                }
            }
            awaitClose { cancel() }
        }

}