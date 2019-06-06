package com.example.chareta.data

import java.util.*

data class Item (
    val id: Long,
    val item_name: String,
    val item_description: String,
    val starting_price: Long,
    val post_date: Date,
    val expiry_date: Date
)