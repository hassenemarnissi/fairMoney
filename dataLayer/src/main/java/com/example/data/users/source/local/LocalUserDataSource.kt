package com.example.data.users.source.local

import com.example.data.users.source.toUser
import com.example.data.users.source.toUserEntity
import com.example.domainlayer.users.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalUserDataSource @Inject constructor(private val dao: IUserDao):ILocalUserDataSource {
    override suspend fun saveUser(User: User) {
        dao.saveUser(User.toUserEntity())
    }

    override suspend fun deleteAllUsers() {
        dao.deleteAllUsers()
    }

    override suspend fun getUsers(limit: Int, page: Int): Flow<List<User>> =
        callbackFlow {
            offer(dao.getUsers(limit,page).map { it.toUser() })
            awaitClose { close() }

    }
}