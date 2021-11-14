package com.example.data.users.source.local

import androidx.room.*

@Dao
interface IUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(User: UserEntity)

    @Query("DELETE FROM userTable")
    suspend fun deleteAllUsers()

    @Transaction
    @Query("SELECT * FROM userTable  LIMIT :limit OFFSET :page")
    suspend fun getUsers(limit: Int, page: Int): List<UserEntity>

}