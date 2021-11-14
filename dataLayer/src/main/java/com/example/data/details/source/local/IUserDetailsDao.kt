package com.example.data.details.source.local

import androidx.room.*
import com.example.data.users.source.local.UserEntity

@Dao
interface IUserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserDetails(userDetails: UserDetailsEntity)

    @Query("DELETE FROM userDetailsTable Where id  = :id ")
    suspend fun deleteUserDetails(id:String)

    @Transaction
    @Query("SELECT * FROM userDetailsTable Where id  = :id ")
    suspend fun getUserDetails(id:String):UserDetailsEntity?

    @Query("DELETE FROM userDetailsTable ")
    suspend fun deleteAllUserDetails()

    @Query("DELETE FROM locationTable")
    suspend fun deleteAllLocation()
}