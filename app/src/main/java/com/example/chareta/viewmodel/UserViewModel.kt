package com.example.chareta.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.R
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.User
import com.example.chareta.data.model.UsersWrapper
import com.example.chareta.repository.UserRepository
import com.example.chareta.data.remote.ServiceBuilder
import com.example.chareta.data.remote.webservice.UserService
import com.example.chareta.view.PostedItemFragment
import com.example.chareta.view.RegisterFragment
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val userRepository: UserRepository

    init {
        val userService = ServiceBuilder.buildService(UserService::class.java)
        val userDao = CharetaDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userService, userDao)
    }

    private  val _getResponse = MutableLiveData<Response<User>>()
    val getResponse: LiveData<Response<User>>
        get() = _getResponse

    private val _getResponses = MutableLiveData<Response<UsersWrapper>>()
    val getResponses: LiveData<Response<UsersWrapper>>
        get() = _getResponses

    private val _updateResponse = MutableLiveData<Response<User>>()
    val updateResponse: LiveData<Response<User>>
        get() = _updateResponse

    private val _insertResponse = MutableLiveData<Response<User>>()
    val insertResponse: LiveData<Response<User>>
        get() = _insertResponse

    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse: MutableLiveData<Response<Void>>
        get() = _deleteResponse


    fun getUsers() = viewModelScope.launch {
        _getResponses.postValue(userRepository.getUsers())
    }

    fun getUserById(id: Long) = viewModelScope.launch {
        _getResponse.postValue(userRepository.getUserById(id))
    }

    fun insertUser(user: User) = viewModelScope.launch {
        _insertResponse.postValue(userRepository.insertUser(user))
    }

    fun updateUser(id: Long, user: User) = viewModelScope.launch {
        _updateResponse.postValue(userRepository.updateUser(id, user))
    }

    fun deleteUser(id: Long) = viewModelScope.launch {
        _deleteResponse.postValue(userRepository.deleteUser(id))
    }
 @Bindable
 val username = MutableLiveData<String>()
  @Bindable
  val password = MutableLiveData<String>()
   @Bindable
   val phoneno= MutableLiveData<String>()
    @Bindable
    val address = MutableLiveData<String>()
    @Bindable
    val confirmPassword = MutableLiveData<String>()
   fun cancelButtonClicked(){

   }

    fun loginButtonClicked(){
        val postedFragment = PostedItemFragment()
        postedFragment.requireFragmentManager()
            .beginTransaction()
            .replace(R.id.container,postedFragment)
            .commit()
    }
    fun notRegisteredClicked(){
        val registerFragment = RegisterFragment()
        registerFragment.requireFragmentManager()
            .beginTransaction()
            .replace(R.id.container,registerFragment)
            .commit()
    }
    fun onRegisterButtonClicked(){

    }
    fun onBckButtonClicked(){

    }



}