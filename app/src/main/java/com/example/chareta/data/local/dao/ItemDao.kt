package com.example.chareta.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chareta.data.local.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items WHERE  id = :id")
    fun getItemById(id: Long): LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItemById(item: Item)

}