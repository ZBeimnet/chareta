package com.example.chareta.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BidsWrapper (
    @SerializedName("_embedded")
    @Expose
    val embeddedItems: BidList
    ) {

    data class BidList(
        @SerializedName("bids")
        @Expose
        val allItems: List<Bid>
    )
}