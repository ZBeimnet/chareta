package com.example.chareta.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.data.remote.model.User
import com.example.chareta.data.remote.model.UsersWrapper
import com.example.chareta.repository.UserRepository
import com.example.chareta.data.remote.webservice.ServiceBuilder
import com.example.chareta.data.remote.webservice.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val userRepository: UserRepository

    init {
        val userService = ServiceBuilder.buildService(UserService::class.java)
        userRepository = UserRepository(userService)
    }

    fun getAllUsers(): LiveData<List<User>> {
        val allUsers: MutableLiveData<List<User>> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<UsersWrapper> = userRepository.getAllUsersAsync().await()
            val responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main) {
                    allUsers.value = responseBody.embeddedUsers.allUsers
                }
            }

        }

        return allUsers
    }

    fun getUserById(id: Long): LiveData<User> {
        val user: MutableLiveData<User> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<User> = userRepository.getUserById(id).await()
            val responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main) {
                    user.value = responseBody
                }
            }

        }

        return user
    }

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Void> = userRepository.addUserAsync(user).await()
            Log.d("user_added", response.message())
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Void> = userRepository.deleteUserByIdAsync(id).await()
            Log.d("user_deleted", response.message())
        }
    }
}