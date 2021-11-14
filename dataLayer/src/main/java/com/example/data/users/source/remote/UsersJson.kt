package com.example.data.users.source.remote

import com.example.domainlayer.users.User

data class UsersJson(
    val data: List<User>,
    val total: Int,
    val page: Int,
    val limit: Int
)