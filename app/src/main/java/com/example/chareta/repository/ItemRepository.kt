package com.example.chareta.repository

import com.example.chareta.data.remote.model.Item
import com.example.chareta.data.remote.model.ItemsWrapper
import com.example.chareta.data.remote.webservice.ItemService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class ItemRepository(private val itemService: ItemService) {

    fun getAllItemsAsync(): Deferred<Response<ItemsWrapper>> {
        return itemService.getItems()
    }

    fun getItemByIdAsync(id: Long): Deferred<Response<Item>> {

        return itemService.getItem(id)
    }

    fun getItemsByUserId(id: Long): Deferred<Response<ItemsWrapper>> {
        return itemService.getItemsByUserId(id)
    }

    fun addItemAsync(item: Item): Deferred<Response<Void>> {
        return itemService.createItem(item)
    }

    fun deleteItemAsync(id: Long): Deferred<Response<Void>> {
        return itemService.deleteItem(id)
    }


}