package com.example.domainlayer.details


class UserDetailsUseCase (private val repository: IUserDetailsRepository) {
    suspend fun execute(id:String) = repository.getUserDetails(id)
}