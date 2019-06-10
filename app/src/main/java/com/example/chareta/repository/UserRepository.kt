package com.example.chareta.repository

import com.example.chareta.data.remote.model.User
import com.example.chareta.data.remote.model.UsersWrapper
import com.example.chareta.data.remote.webservice.UserService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class UserRepository(private val userService: UserService) {

    fun getAllUsersAsync(): Deferred<Response<UsersWrapper>> {
        return userService.getUsersAsync()
    }

    fun getUserById(id: Long): Deferred<Response<User>> {
        return userService.getUserByIdAsync(id)
    }

    fun addUserAsync(user: User): Deferred<Response<Void>> {
        return userService.createUserAsync(user)
    }

    fun deleteUserByIdAsync(id: Long): Deferred<Response<Void>> {
        return userService.deleteUserByIdAsync(id)
    }
}