package com.example.chareta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.data.Item
import com.example.chareta.repository.ItemRepository
import com.example.chareta.webservice.ItemService
import com.example.chareta.webservice.ServiceBuilder
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

    fun getAllIems(): LiveData<List<Item>> {
        val allItems: MutableLiveData<List<Item>> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<List<Item>> = itemRepository.getAllItemsAsync().await()
            withContext(Dispatchers.Main){
                allItems.value = response.body()
            }
        }

        return allItems

    }

    fun getItemById(id: Long): LiveData<Item> {
        val item: MutableLiveData<Item> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<Item> = itemRepository.getItemByIdAsync(id).await()
            withContext(Dispatchers.Main){
                item.value = response.body()
            }

        }

        return item
    }



}