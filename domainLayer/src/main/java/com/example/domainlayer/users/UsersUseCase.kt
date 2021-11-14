package com.example.domainlayer.users

class UsersUseCase(private val repository: IUsersRepository) {
    suspend fun execute(page: Int, limit: Int) = repository.getUsers(page, limit)
}