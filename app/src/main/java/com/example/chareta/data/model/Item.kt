package com.example.chareta.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "items")
data class Item (

    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("item_name")
    val item_name: String,

    @SerializedName("item_description")
    val item_description: String,

    @SerializedName("starting_price")
    val starting_price: Long,

    @SerializedName("post_date")
    val post_date: String?,

    @SerializedName("expiry_date")
    val expiry_date: String

):Serializable

