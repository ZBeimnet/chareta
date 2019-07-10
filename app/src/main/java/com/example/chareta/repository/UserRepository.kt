package com.example.chareta.repository

import androidx.lifecycle.LiveData
import com.example.chareta.data.local.dao.UserDao
import com.example.chareta.data.model.User
import com.example.chareta.data.model.UsersWrapper
import com.example.chareta.data.remote.webservice.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val userService: UserService, private val userDao: UserDao) {

    private fun saveUsersToLocal(users: List<User>) {
        for(user in users) {
            userDao.insertUser(user)
        }
    }

    private fun saveUserToLocal(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUserFromLocal(): LiveData<List<User>> =
        withContext(Dispatchers.IO) {
            userDao.getUsers()
        }

    private fun deleteUserFromLocal(userId: Long) {
        userDao.deleteUserById(userId)
    }

    suspend fun getUsers(): Response<UsersWrapper> {
        lateinit var users: Response<UsersWrapper>
        withContext(Dispatchers.IO) {
            val allUsers = userService.getUsersAsync().await()
            saveUsersToLocal(allUsers.body()!!.embeddedUsers.allUsers)
            withContext(Dispatchers.Main){
                users = allUsers
            }
        }

        return users
    }

    suspend fun getUserById(id: Long): Response<User> =
        withContext(Dispatchers.IO) {
            userService.getUserAsync(id).await()
    }

    suspend fun insertUser(user: User): Response<Void> =
        withContext(Dispatchers.IO) {
            saveUserToLocal(user)
            userService.insertUserAsync(user).await()
    }

    suspend fun updateUser(id: Long, user: User): Response<User> =
        withContext(Dispatchers.IO) {
            saveUserToLocal(user)
            userService.updateUserAsync(id, user).await()
    }

    suspend fun deleteUser(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
            deleteUserFromLocal(id)
            userService.deleteUserAsync(id).await()
    }
}