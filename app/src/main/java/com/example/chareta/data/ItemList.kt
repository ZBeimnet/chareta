package com.example.chareta.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemList (
    @SerializedName("items")
    @Expose
    val itemLists: List<Item>

)