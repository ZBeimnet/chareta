package com.example.chareta.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "bids")
data class Bid (

    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("bid_amount")
    val bid_amount: Long,

    @SerializedName("bid_date")
    val bid_date: String

): Serializable