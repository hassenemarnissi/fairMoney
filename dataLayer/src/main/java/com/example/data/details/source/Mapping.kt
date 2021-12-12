package com.example.data.details.source

import com.example.data.details.source.local.LocationEntity
import com.example.data.details.source.local.UserDetailsEntity
import com.example.domainlayer.details.Location
import com.example.domainlayer.details.UserDetails


internal fun UserDetails.toUserDetailsEntity() = UserDetailsEntity(
    this.id,
    title,
    firstName,
    lastName,
    picture,
    gender,
    email,
    dateOfBirth,
    phone,
    //LocationEntity(idLocation=location.idLocation,street = location.street,city = location.city,state=location.state,country = location.country,timezone = location.timezone),
    registerDate,
    updatedDate
)
internal fun UserDetailsEntity.toUserDetails()=
    UserDetails( this.id,
        title,
        firstName,
        lastName,
        picture,
        gender,
        email,
        dateOfBirth,
        phone,
       // Location(idLocation= location.idLocation,street = location.street,city = location.city,state=location.state,country = location.country,timezone = location.timezone)
        registerDate,
        updatedDate
        )

