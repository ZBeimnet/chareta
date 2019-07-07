package com.example.chareta.data.remote.webservice

import com.example.chareta.data.remote.model.User
import com.example.chareta.data.remote.model.UsersWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface UserService {

    @GET("users")
    fun getUsersAsync(): Deferred<Response<UsersWrapper>>

    @GET("users/{id}")
    fun getUserAsync(@Path("id") id: Long): Deferred<Response<User>>

    @POST("users")
    fun insertUserAsync(@Body newUser: User): Deferred<Response<User>>

    @PUT("users/{id}")
    fun updateUserAsync(@Path("id") id: Long, @Body user: User): Deferred<Response<User>>

    @DELETE("users/id")
    fun deleteUserAsync(@Path("id") id: Long): Deferred<Response<Void>>
}