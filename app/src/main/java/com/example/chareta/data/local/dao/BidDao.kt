package com.example.chareta.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chareta.data.model.Bid

@Dao
interface BidDao {

    @Query("SELECT * FROM bids WHERE  id = :id")
    fun getBidById(id: Long): LiveData<Bid>

    @Query("SELECT * FROM bids")
    fun getBids(): LiveData<List<Bid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBid(bid: Bid)

    @Query("DELETE FROM bids WHERE id = :bidId")
    fun deleteBidById(bidId: Long)

}