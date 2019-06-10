package com.example.chareta.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Bid (

    @SerializedName("id")
    val id: Long,

    @SerializedName("bid_amount")
    val bid_amount: Long,

    @SerializedName("bid_date")
    val bid_date: Date

): Serializable