package com.example.chareta.repository

import androidx.lifecycle.LiveData
import com.example.chareta.data.Item
import com.example.chareta.webservice.ItemService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class ItemRepository(private val itemService: ItemService) {

    fun getAllItemsAsync(): Deferred<Response<List<Item>>> {
        return itemService.getItems()
    }

    fun getItemByIdAsync(id: Long): Deferred<Response<Item>> {
        return itemService.getItem(id)
    }
}