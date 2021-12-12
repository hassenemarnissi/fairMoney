package com.example.presentationlayer.users

import androidx.lifecycle.LiveData
import com.example.domainlayer.common.Result
import com.example.presentationlayer.details.UserDetailsUi

interface IUserViewModel {
     fun observeUsers(): LiveData<Result<List<Pair<UserUi,String>>>>
     fun observeUserDetails(): LiveData<Result<UserDetailsUi>>
}