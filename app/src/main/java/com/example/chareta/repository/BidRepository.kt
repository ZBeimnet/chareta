package com.example.chareta.repository

import androidx.lifecycle.LiveData
import com.example.chareta.data.local.dao.BidDao
import com.example.chareta.data.model.Bid
import com.example.chareta.data.model.BidsWrapper
import com.example.chareta.data.remote.webservice.BidService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class BidRepository(private val bidService: BidService, private val bidDao: BidDao) {

    private fun saveBidsToLocal(bids: List<Bid>) {
        for(bid in bids) {
            bidDao.insertBid(bid)
        }
    }

    private fun saveBidToLocal(bid: Bid) {
        bidDao.insertBid(bid)
    }

    suspend fun getBidsFromLocal(): LiveData<List<Bid>> =
        withContext(Dispatchers.IO) {
            bidDao.getBids()
    }

    private fun deleteBidFromLocal(bidId: Long) {
        bidDao.deleteBidById(bidId)
    }

    suspend fun getBids(): Response<BidsWrapper> =
//        lateinit var bids: Response<BidsWrapper>
        withContext(Dispatchers.IO) {
            bidService.getBidsAsync().await()
//            saveBidsToLocal(allBids.body()!!.embeddedBids.allBids)     //saving bids to local
//            withContext(Dispatchers.Main) {
//                bids = allBids
//            }
        }

//        return bids
//    }

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
//            saveBidToLocal(bid)
            bidService.insertBidAsync(bid).await()
        }

    suspend fun updateItem(id: Long, bid: Bid): Response<Bid> =
        withContext(Dispatchers.IO) {
//            saveBidToLocal(bid)
            bidService.updateBidAsync(id, bid).await()
        }

    suspend fun deleteItem(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
//            deleteBidFromLocal(id)
            bidService.deleteBidAsync(id).await()
        }
}