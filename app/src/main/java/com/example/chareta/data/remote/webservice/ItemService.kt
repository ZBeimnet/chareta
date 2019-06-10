package com.example.chareta.webservice

import com.example.chareta.model.Item
import com.example.chareta.model.ItemsWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import java.util.*


interface ItemService {

    @GET("items")
    fun getItems(): Deferred<Response<ItemsWrapper>>

    @GET("items/{id}")
    fun getItem(@Path("id") id: Long): Deferred<Response<Item>>

    @GET("users/{id}/items")
    fun getItemsByUserId(@Path("id") id: Long): Deferred<Response<ItemsWrapper>>

    @POST("items")
    fun createItem(@Body newItem: Item): Deferred<Response<Void>>

    @FormUrlEncoded
    @PUT("items/id")
    fun updateItem(
        @Path("id") id: Int,
        @Field("item_name") item_name: String,
        @Field("item_description") item_description: String,
        @Field("starting_price") starting_price: Long,
        @Field("post_date") date: Date,
        @Field("expiry_date") expiry_date: Date
    ): Deferred<Response<Void>>

    @DELETE("items/id")
    fun deleteItem(@Path("id") id: Long): Deferred<Response<Void>>
}