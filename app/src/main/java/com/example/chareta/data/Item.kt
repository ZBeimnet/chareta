package com.example.chareta.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
<<<<<<< HEAD

import java.sql.Date

=======
import java.io.Serializable
import java.util.*
>>>>>>> 3ff455dbe7c1bf2e00ec2081659a1bef181f0d92

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