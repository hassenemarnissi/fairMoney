package com.example.presentationlayer.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domainlayer.users.User
import com.example.domainlayer.users.UsersUseCase
import com.example.domainlayer.common.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception


@ExperimentalCoroutinesApi
class UserViewModel @ViewModelInject constructor(private val userUseCase: UsersUseCase) : ViewModel(), IUserViewModel {

    private val users = MutableLiveData<Result<List<UserUi>>>()

    init {
        fetchUser()
    }
    override fun observeUsers(): LiveData<Result<List<UserUi>>> = users

    private fun fetchUsers(page:Int, limit:Int) {
        viewModelScope.launch {
            users.postValue(Result.Loading)
            try {
                delay(2000)
                userUseCase.execute(page,limit).collect { userResult ->
                    when (userResult) {
                        is Result.Success -> {
                            val usersUi = userResult.data.map { it.toUserUi() }
                            users.postValue(Result.Success(usersUi))
                        }
                        is Result.Failure -> users.postValue(Result.Failure(userResult.exception))
                        Result.Loading -> {
                            // DO Nothing
                        }
                    }

                }
            } catch (e: Exception) {
                users.postValue(Result.Failure(e))
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            try {
                val result = retrieveUserUseCase.execute()
                if (result is Result.Success) {
                    val userUi = result.data.toUi()
                    user.postValue(Result.Success(userUi))
                    isValidUser.postValue(userUi.isValid)
                }
            } catch (e: Exception) {
                user.postValue(Result.Failure(e))
            }
        }
    }
}