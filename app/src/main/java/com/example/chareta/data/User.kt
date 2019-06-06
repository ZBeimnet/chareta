package com.example.chareta.data

data class User (
    private val id: Long,
    private val user_name: String,
    private val phone_number: String,
    private val address: String,
    private val password: String
)