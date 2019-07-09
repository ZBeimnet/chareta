package com.example.chareta.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BidsWrapper (
    @SerializedName("_embedded")
    @Expose
    val embeddedBids: BidList
    ) {

    data class BidList(
        @SerializedName("bids")
        @Expose
        val allBids: List<Bid>
    )
}