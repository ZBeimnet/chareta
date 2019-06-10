package com.example.chareta.webservice

import com.example.chareta.model.Bid
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BidService {

    @GET("items/{id}/bids")
    fun getBidsByItemId(@Path("id") id: Long): Deferred<Response<List<Bid>>>
}