package com.example.chareta.repository

import com.example.chareta.data.Item
import com.example.chareta.data.ItemList
import com.example.chareta.webservice.ItemService
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

class ItemRepository(private val itemService: ItemService) {

    fun getAllItemsAsync(): Deferred<Response<ItemList>> {
        return itemService.getItems()
    }

    fun getItemByIdAsync(id: Long): Deferred<Response<Item>> {
        return itemService.getItem(id)
    }
}