package com.example.chareta.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.chareta.data.local.dao.ItemDao
import com.example.chareta.data.model.Item
import com.example.chareta.data.model.ItemsWrapper
import com.example.chareta.data.remote.webservice.ItemService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ItemRepository(private val itemService: ItemService, private val itemDao: ItemDao) {

    private fun saveItemsToLocal(items: List<Item>) {
        for(item in items) {
            itemDao.insertItem(item)
        }
    }

    private fun saveItemToLocal(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun getItemsFromLocal(): LiveData<List<Item>> =
        withContext(Dispatchers.IO) {
            itemDao.getItems()
    }

    private fun deleteItemFromLocal(itemId: Long) {
        itemDao.deleteItemById(itemId)
    }

    suspend fun getItems(): Response<ItemsWrapper> {
        lateinit var items: Response<ItemsWrapper>
        withContext(Dispatchers.IO) {
            val allItems = itemService.getItemsAsync().await()
            saveItemsToLocal(allItems.body()!!.embeddedItems.allItems)  //saving item to a local database
            withContext(Dispatchers.Main) {
                items = allItems
            }
        }
        val i: Int = items.body()!!.embeddedItems.allItems.size
        Log.d("items", i.toString() )

        return items
    }

    suspend fun getItemById(id: Long): Response<Item> =
        withContext(Dispatchers.IO) {
            itemService.getItemAsync(id).await()
    }

    suspend fun getItemsByUserId(id: Long): Response<ItemsWrapper> =
        withContext(Dispatchers.IO) {
            itemService.getItemsByUserIdAsync(id).await()
    }

    suspend fun insertItem(item: Item): Response<Void> =
        withContext(Dispatchers.IO) {
//            saveItemToLocal(item)   //updating the local database
            itemService.insertItemAsync(item).await()
    }

    suspend fun updateItem(id: Long, item: Item): Response<Item> =
        withContext(Dispatchers.IO) {
//            saveItemToLocal(item)    //updating the local database
            itemService.updateItemAsync(id, item).await()
    }

    suspend fun deleteItem(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
//            deleteItemFromLocal(id)
            itemService.deleteItemAsync(id).await()
    }

}