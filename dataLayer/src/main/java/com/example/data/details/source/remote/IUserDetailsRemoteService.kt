package com.example.data.details.source.remote

import com.example.domainlayer.details.UserDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserDetailsRemoteService {
    @GET("/user/{id}")
    suspend fun getUserDetails(@Path("id") id:String): UserDetails
}