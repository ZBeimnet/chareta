package com.example.chareta.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class User (
    val id: Long,
    val user_name: String,
    val phone_number: String,
    val address: String,
    val password: String
)