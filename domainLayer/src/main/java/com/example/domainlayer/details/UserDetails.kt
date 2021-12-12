package com.example.domainlayer.details

import java.util.*

data class UserDetails (
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val phone: String,
    //val location: Location,
    val registerDate: String,
    val updatedDate:String
)
data class Location (
    val idLocation:Int,
    val street: String,
    val city:String,
    val state:String,
    val country:String,
    val timezone:String
)