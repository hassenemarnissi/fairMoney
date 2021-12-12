package com.example.data.users.source.remote
import retrofit2.http.GET
import retrofit2.http.Query

interface IUserRemoteService {
    @GET("/data/v1/user")
    suspend fun getUsers(@Query("page") page: Int, @Query("limit") limit: Int): UsersJson
}




