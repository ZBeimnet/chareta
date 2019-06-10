package com.example.chareta.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemsWrapper (
    @SerializedName("_embedded")
    @Expose
    val embeddedItems: ItemList
) {

    data class ItemList(
        @SerializedName("items")
        @Expose
        val allItems: List<Item>
    )

}