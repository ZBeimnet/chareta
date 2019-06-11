package com.example.chareta.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chareta.data.local.model.Bid

@Dao
interface BidDao {

    @Query("SELECT * FROM bids WHERE  id = :id")
    fun getBidById(id: Long): LiveData<Bid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBid(bid: Bid)

    @Update
    fun updateBid(bid: Bid)

    @Delete
    fun deleteBidById(bid: Bid)

}