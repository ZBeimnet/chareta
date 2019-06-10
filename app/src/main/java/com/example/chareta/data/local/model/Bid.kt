package com.example.chareta.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="bids", foreignKeys = arrayOf(
    ForeignKey(entity = Item::class, parentColumns = arrayOf("id"), childColumns = arrayOf("item_id"), onDelete=CASCADE)
))
data class Bid (

    @PrimaryKey @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name="bid_amount")
    val bid_amount: Long,

    @ColumnInfo(name="bid_date")
    val bid_date: String,

    @ColumnInfo(name="item_id", index=true)
    val item_id: Long

):Serializable

