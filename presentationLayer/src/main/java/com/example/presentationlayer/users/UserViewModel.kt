package com.example.presentationlayer.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domainlayer.users.UsersUseCase
import com.example.domainlayer.common.Result
import com.example.domainlayer.details.UserDetails
import com.example.domainlayer.details.UserDetailsUseCase
import com.example.presentationlayer.details.UserDetailsUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception


@ExperimentalCoroutinesApi
class UserViewModel @ViewModelInject constructor(
    private val userUseCase: UsersUseCase,
    private val userDetailsUseCase: UserDetailsUseCase
) :
    ViewModel(), IUserViewModel {

    private val users = MutableLiveData<Result<List<Pair<UserUi, String>>>>()
    private var userDetails = MutableLiveData<Result<UserDetailsUi>>()

    init {
        fetchUsers(0, 10)
    }

    override fun observeUsers(): LiveData<Result<List<Pair<UserUi, String>>>> = users
    override fun observeUserDetails(): LiveData<Result<UserDetailsUi>> = userDetails
    private fun fetchUsers(page: Int, limit: Int) {
        viewModelScope.launch {
            users.postValue(Result.Loading)
            try {
                delay(2000)
                userUseCase.execute(page, limit).collect { userResult ->
                    when (userResult) {
                        is Result.Success -> {
                            val pairUser = userResult.data.map { Pair(it.toUserUi(), it.id) }

                            users.postValue(Result.Success(pairUser))
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

    fun getUserDetails(userId: String) {
        viewModelScope.launch {
            userDetails.postValue(Result.Loading)
            try {
                delay(2000)
                userDetailsUseCase.execute(userId).collect { userDetailsResult ->
                    when (userDetailsResult) {
                        is Result.Success -> {
                            val userDetailsData = userDetailsResult.data.toUserDetailsUi()
                            userDetails.postValue(Result.Success(userDetailsData))
                        }
                        is Result.Failure -> userDetails.postValue(Result.Failure(userDetailsResult.exception))
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
}