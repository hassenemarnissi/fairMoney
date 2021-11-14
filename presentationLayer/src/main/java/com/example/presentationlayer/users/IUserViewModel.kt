package com.example.presentationlayer.users

import androidx.lifecycle.LiveData
import com.example.domainlayer.common.Result

interface IUserViewModel {
     fun observeUsers(): LiveData<Result<List<UserUi>>>
}