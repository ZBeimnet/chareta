package com.example.chareta.data.remote.webservice

import com.example.chareta.data.model.Bid
import com.example.chareta.data.model.BidsWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface BidService {

    @GET("bids")
    fun getBidsAsync(): Deferred<Response<BidsWrapper>>

    @GET("bids/{id}")
    fun getBidAsync(@Path("id") id: Long): Deferred<Response<Bid>>

    @GET("items/{id}/bids")
    fun getBidsByItemIdAsync(@Path("id") id: Long): Deferred<Response<List<Bid>>>

    @POST("bids")
    fun insertBidAsync(@Body bid: Bid): Deferred<Response<Bid>>

    @PUT("bids/{id}")
    fun updateBidAsync(@Path("id") id: Long, @Body bid: Bid): Deferred<Response<Bid>>

    @DELETE("bids/{id}")
    fun deleteBidAsync(@Path("id") id: Long): Deferred<Response<Void>>

}