package com.example.chareta.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemsEmbedded (
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