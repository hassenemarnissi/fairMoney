package com.example.presentationlayer.users

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserUi (val name:String,val picture:String):Parcelable
