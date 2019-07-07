package com.example.chareta.repository

import com.example.chareta.data.remote.model.Bid
import com.example.chareta.data.remote.model.BidsWrapper
import com.example.chareta.data.remote.webservice.BidService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class BidRepository(private val bidService: BidService) {

    suspend fun getBids(): Response<BidsWrapper> =
        withContext(Dispatchers.IO) {
            bidService.getBidsAsync().await()
        }

    suspend fun getBidById(id: Long): Response<Bid> =
        withContext(Dispatchers.IO) {
            bidService.getBidAsync(id).await()
        }

    suspend fun getBidsByItemId(id: Long): Response<List<Bid>> =
        withContext(Dispatchers.IO) {
            bidService.getBidsByItemIdAsync(id).await()
        }

    suspend fun insertBid(bid: Bid): Response<Bid> =
        withContext(Dispatchers.IO) {
            bidService.insertBidAsync(bid).await()
        }

    suspend fun updateItem(id: Long, bid: Bid): Response<Bid> =
        withContext(Dispatchers.IO) {
            bidService.updateBidAsync(id, bid).await()
        }

    suspend fun deleteItem(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
            bidService.deleteBidAsync(id).await()
        }

}