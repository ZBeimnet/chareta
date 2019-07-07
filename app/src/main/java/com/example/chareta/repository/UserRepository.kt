package com.example.chareta.repository

import com.example.chareta.data.remote.model.User
import com.example.chareta.data.remote.model.UsersWrapper
import com.example.chareta.data.remote.webservice.UserService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val userService: UserService) {

    suspend fun getUsers(): Response<UsersWrapper> =
        withContext(Dispatchers.IO) {
            userService.getUsersAsync().await()
    }

    suspend fun getUserById(id: Long): Response<User> =
        withContext(Dispatchers.IO) {
            userService.getUserAsync(id).await()
    }

    suspend fun insertUser(user: User): Response<User> =
        withContext(Dispatchers.IO) {
            userService.insertUserAsync(user).await()
    }

    suspend fun updateUser(id: Long, user: User): Response<User> =
        withContext(Dispatchers.IO) {
            userService.updateUserAsync(id, user).await()
    }

    suspend fun deleteUser(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
            userService.deleteUserAsync(id).await()
    }
}