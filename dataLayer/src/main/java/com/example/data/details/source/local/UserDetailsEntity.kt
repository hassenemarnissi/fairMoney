package com.example.data.details.source.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName ="userDetailsTable")
data class UserDetailsEntity (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title", defaultValue = "")
    val title: String,
    @ColumnInfo(name = "firstName", defaultValue = "")
    val firstName: String,
    @ColumnInfo(name = "lastName", defaultValue = "")
    val lastName: String,
    @ColumnInfo(name = "picture", defaultValue = "")
    val picture: String,
    @ColumnInfo(name = "gender", defaultValue = "")
    val gender: String,
    @ColumnInfo(name = "email", defaultValue = "")
    val email: String,
    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String,
    @ColumnInfo(name = "phone", defaultValue = "")
    val phone: String,
    //@ColumnInfo(name = "location", defaultValue = "")
    //@Embedded val location: LocationEntity,
    @ColumnInfo(name = "registerDate")
    val registerDate: String,
    @ColumnInfo(name = "updatedDate")
    val updatedDate: String
)

@Entity(tableName ="locationTable")
data class LocationEntity (
    @PrimaryKey(autoGenerate = true)
    val idLocation:Int ,
    @ColumnInfo(name = "street", defaultValue = "")
    val street: String,
    @ColumnInfo(name = "city", defaultValue = "")
    val city:String,
    @ColumnInfo(name = "state", defaultValue = "")
    val state:String,
    @ColumnInfo(name = "country", defaultValue = "")
    val country:String,
    @ColumnInfo(name = "timezone", defaultValue = "")
    val timezone:String
)