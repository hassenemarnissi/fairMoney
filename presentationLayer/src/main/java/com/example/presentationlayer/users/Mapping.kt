package com.example.presentationlayer.users

import com.example.domainlayer.users.User
import java.util.*

internal fun User.toUserUi() = UserUi(name = "${title.toUpperCase(Locale.ROOT)} $firstName $lastName",picture=picture)