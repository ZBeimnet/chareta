package com.example.chareta.repository

import com.example.chareta.data.remote.model.Bid
import com.example.chareta.data.remote.webservice.BidService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class BidRepository(private val bidService: BidService) {

    fun getBidsByItemIdAsync(id: Long): Deferred<Response<List<Bid>>> {
        return bidService.getBidsByItemId(id)
    }
}