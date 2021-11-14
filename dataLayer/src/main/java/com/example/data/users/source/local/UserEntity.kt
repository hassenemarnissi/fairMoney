package com.example.data.users.source.local
import androidx.room.*

@Entity(tableName = "userTable")
data class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title", defaultValue = "")
    val title: String = "",
    @ColumnInfo(name = "firstName", defaultValue = "")
    val firstName: String = "",
    @ColumnInfo(name = "lastName", defaultValue = "")
    val lastName: String = "",
    @ColumnInfo(name = "picture", defaultValue = "")
    val picture: String = ""
)
