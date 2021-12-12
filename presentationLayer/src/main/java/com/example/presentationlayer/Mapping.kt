package com.example.presentationlayer.users

import com.example.domainlayer.details.UserDetails
import com.example.domainlayer.users.User
import com.example.presentationlayer.details.UserDetailsUi
import java.util.*

internal fun User.toUserUi() = UserUi(name = "${title.toUpperCase(Locale.ROOT)} $firstName $lastName",picture=picture)
internal fun UserDetails.toUserDetailsUi() = UserDetailsUi(
    picture = picture,
    name = "${title.toUpperCase(Locale.ROOT)} $firstName $lastName",
    dateOfBirth = "$dateOfBirth",
    //address = "${location.street}, ${location.city},${location.state}, ${location.country}",
    phone = "$phone",
    email = "$email"
)