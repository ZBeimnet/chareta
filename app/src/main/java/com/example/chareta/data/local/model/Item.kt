package com.example.chareta.data.local.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.io.Serializable

@Entity(tableName="items", foreignKeys = arrayOf(
    ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"), onDelete=CASCADE)
))
data class Item (

    @PrimaryKey @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name="item_name")
    val item_name: String,

    @ColumnInfo(name="item_description")
    val item_description: String,

    @ColumnInfo(name="starting_price")
    val starting_price: Long,

    @ColumnInfo(name="post_date")
    val post_date: String,

    @ColumnInfo(name="expiry_data")
    val expiry_date: String,

    @ColumnInfo(name="user_id", index=true)
    val user_id: Long

):Serializable