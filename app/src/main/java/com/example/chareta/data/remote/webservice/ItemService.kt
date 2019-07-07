package com.example.chareta.data.remote.webservice

import com.example.chareta.data.remote.model.Item
import com.example.chareta.data.remote.model.ItemsWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import java.util.*


interface  ItemService {

    @GET("items")
    fun getItemsAsync(): Deferred<Response<ItemsWrapper>>

    @GET("items/{id}")
    fun getItemAsync(@Path("id") id: Long): Deferred<Response<Item>>

    @GET("users/{id}/items")
    fun getItemsByUserIdAsync(@Path("id") id: Long): Deferred<Response<List<Item>>>

    @POST("items")
    fun insertItemAsync(@Body newItem: Item): Deferred<Response<Item>>

    @PUT("items/{id}")
    fun updateItemAsync(@Path("id") id: Long, @Body item: Item): Deferred<Response<Item>>

    @DELETE("items/id")
    fun deleteItemAsync(@Path("id") id: Long): Deferred<Response<Void>>
}