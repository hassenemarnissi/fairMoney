package com.example.data.users.source

import com.example.data.users.source.local.UserEntity
import com.example.domainlayer.users.User

internal fun User.toUserEntity() = UserEntity(
    id,
    title,
    firstName,
    lastName,
    picture
)
internal fun UserEntity.toUser() = User(
    id,
    title,
    firstName,
    lastName,
    picture
)