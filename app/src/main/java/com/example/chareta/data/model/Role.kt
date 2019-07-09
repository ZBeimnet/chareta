package com.example.chareta.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "roles")
data class Role (

    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("role")
    val role: String

)
