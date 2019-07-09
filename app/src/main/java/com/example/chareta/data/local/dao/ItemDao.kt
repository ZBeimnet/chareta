package com.example.chareta.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chareta.data.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items WHERE  id = :id")
    fun getItemById(id: Long): LiveData<Item>

    @Query("SELECT * FROM items")
    fun getItems(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Query("DELETE FROM items WHERE id = :itemId")
    fun deleteItemById(itemId: Long)

}