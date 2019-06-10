package com.example.chareta.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



import java.sql.Date

import java.io.Serializable
import java.util.*



data class Item (
    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("item_name")
    @Expose
    val item_name: String,

    @SerializedName("item_description")
    @Expose
    val item_description: String,

    @SerializedName("starting_price")
    @Expose
    val starting_price: Long,

    @SerializedName("post_date")
    @Expose
    val post_date: String,

    @SerializedName("expiry_date")
    @Expose
    val expiry_date: String
)

