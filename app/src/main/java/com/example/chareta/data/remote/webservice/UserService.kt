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
    fun getUserByIdAsync(@Path("id") id: Long): Deferred<Response<User>>

    @POST("users")
    fun createUserAsync(@Body newUser: User): Deferred<Response<Void>>

    @FormUrlEncoded
    @PUT("users/id")
    fun updateUser(
        @Path("id") id: Long,
        @Field("user_name") user_name: String,
        @Field("phone_number") phone_number: String,
        @Field("address") address: String,
        @Field("password") password: String
    ): Deferred<Response<Void>>

    @DELETE("users/id")
    fun deleteUserByIdAsync(@Path("id") id: Long): Deferred<Response<Void>>
}