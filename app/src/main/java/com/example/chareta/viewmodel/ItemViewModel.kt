package com.example.chareta.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.data.remote.model.Item
import com.example.chareta.data.remote.model.ItemsWrapper
import com.example.chareta.repository.ItemRepository
import com.example.chareta.data.remote.webservice.ItemService
import com.example.chareta.data.remote.webservice.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val itemRepository: ItemRepository

    init {
        val itemService = ServiceBuilder.buildService(ItemService::class.java)
        itemRepository = ItemRepository(itemService)
    }

    fun getAllItems(): LiveData<List<Item>> {
        val allItems: MutableLiveData<List<Item>> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<ItemsWrapper> = itemRepository.getAllItemsAsync().await()
            val responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main) {
                    allItems.value = responseBody.embeddedItems.allItems
                }
            }

        }


        Log.i("GET ALL ITEMS", "ALL ITEMS$allItems")
        return allItems

    }

    fun getItemById(id: Long): LiveData<Item> {
        val item: MutableLiveData<Item> = MutableLiveData()


        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Item> = itemRepository.getItemByIdAsync(id).await()
            val  responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main){
                    item.value = response.body()
                }
            }
        }

        return item
    }


    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Void> = itemRepository.addItemAsync(item).await()
            Log.d("item_added", response.message())
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Void> = itemRepository.deleteItemAsync(id).await()
            Log.d("item_deleted", response.message())
        }
    }

    fun getItemsByUserId(userId: Long): LiveData<List<Item>> {
        val allItems: MutableLiveData<List<Item>> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<ItemsWrapper> = itemRepository.getItemsByUserId(userId).await()
            val responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main) {
                    allItems.value = responseBody.embeddedItems.allItems
                }
            }

        }

        Log.i("GET ALL ITEMS", "ALL ITEMS$allItems")
        return allItems

    }


}