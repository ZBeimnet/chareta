package com.example.chareta.repository

import com.example.chareta.data.Item
import com.example.chareta.data.ItemsWrapper
import com.example.chareta.webservice.ItemService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.URI

class ItemRepository(private val itemService: ItemService) {

    fun getAllItemsAsync(): Deferred<Response<ItemsWrapper>> {
        return itemService.getItems()
    }

    fun getItemByIdAsync(id: Long): Deferred<Response<Item>> {
        return itemService.getItem(id)
    }

    fun addItemAsync(item: Item): Deferred<Response<Void>> {
        return itemService.createItem(item)
    }

    fun deleteItemAsync(id: Long): Deferred<Response<Void>> {
        return itemService.deleteItem(id)
    }


}