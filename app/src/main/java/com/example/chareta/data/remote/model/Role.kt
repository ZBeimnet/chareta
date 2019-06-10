package com.example.chareta.data.remote.model

import com.google.gson.annotations.SerializedName

data class Role (

    @SerializedName("id")
    val id: Long,

    @SerializedName("role")
    val role: String

)
