package com.example.chareta.data.remote.model

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id")
    val id: Long,

    @SerializedName("user_name")
    val user_name: String,

    @SerializedName("phone_number")
    val phone_number: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("password")
    val password: String

)