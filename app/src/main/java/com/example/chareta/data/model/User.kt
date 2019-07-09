package com.example.chareta.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User (

    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("user_name")
    val user_name: String,

    @SerializedName("phone_number")
    val phone_number: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("password")
    val password: String

)