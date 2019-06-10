package com.example.chareta.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="users")
data class User (

    @PrimaryKey @ColumnInfo(name="id")
    val id: Long,

    @ColumnInfo(name="user_name")
    val user_name: String,

    @ColumnInfo(name="phone_number")
    val phone_number: String,

    @ColumnInfo(name="address")
    val address: String,

    @ColumnInfo(name="password")
    val password: String

): Serializable