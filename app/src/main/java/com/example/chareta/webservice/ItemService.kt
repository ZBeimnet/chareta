package com.example.webapitest.services

import com.example.chareta.data.Item
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ItemService {
    @GET("item")
    fun getItems(): Call<List<Item>>

    @GET("items/{id}")
    fun getItem(@Path("id") id: Int): Call<Item>

    @POST("item")
    fun createItem(@Body newItem: Item): Call<Item>

    @FormUrlEncoded
    @PUT("item/id")
    fun updateItem(
        @Path("id") id: Int,
        @Field("item_name") item_name: String,
        @Field("item_description") item_description: String,
        @Field("starting_price") starting_price: Long,
        @Field("post_date") date: Date,
        @Field("expiry_date") expiry_date: Date
    ): Call<Item>

    @DELETE("item/id")
    fun deleteItem(@Path("id") id:Int):Call<Void>
}