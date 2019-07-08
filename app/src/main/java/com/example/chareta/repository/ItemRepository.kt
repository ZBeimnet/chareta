package com.example.chareta.repository

import com.example.chareta.data.remote.model.Item
import com.example.chareta.data.remote.model.ItemsWrapper
import com.example.chareta.data.remote.webservice.ItemService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ItemRepository(private val itemService: ItemService) {

    suspend fun getItems(): Response<ItemsWrapper> =
        withContext(Dispatchers.IO) {
            itemService.getItemsAsync().await()
    }

    suspend fun getItemById(id: Long): Response<Item> =
        withContext(Dispatchers.IO) {
            itemService.getItemAsync(id).await()
    }

    suspend fun getItemsByUserId(id: Long): Response<ItemsWrapper> =
        withContext(Dispatchers.IO) {
            itemService.getItemsByUserIdAsync(id).await()
    }

    suspend fun insertItem(item: Item): Response<Item> =
        withContext(Dispatchers.IO) {
            itemService.insertItemAsync(item).await()
    }

    suspend fun updateItem(id: Long, item: Item): Response<Item> =
        withContext(Dispatchers.IO) {
            itemService.updateItemAsync(id, item).await()
    }

    suspend fun deleteItem(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
            itemService.deleteItemAsync(id).await()
    }


}