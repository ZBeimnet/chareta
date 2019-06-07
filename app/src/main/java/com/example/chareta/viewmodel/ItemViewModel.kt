package com.example.chareta.viewmodel

import android.app.Application
import android.app.LauncherActivity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.data.Item
import com.example.chareta.data.ItemList
import com.example.chareta.repository.ItemRepository
import com.example.chareta.webservice.ItemService
import com.example.chareta.webservice.ServiceBuilder
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.util.*

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val itemRepository: ItemRepository

    init {
        val itemService = ServiceBuilder.buildService(ItemService::class.java)
        itemRepository = ItemRepository(itemService)
    }

    fun getAllItems(): LiveData<List<Item>> {
        val allItems: MutableLiveData<List<Item>> = MutableLiveData()

//        viewModelScope.launch(Dispatchers.IO) {
//
//            val call = itemRepository.getAllItemsAsync()
//            call.enqueue(object: Callback<ItemList> {
//                override fun onFailure(call: Call<ItemList>, t: Throwable) {
//                    Log.d("isFailed", t.message)
//                }
//
//                override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
//                    allItems.value = response.body()?.itemLists
//                    //Log.d("worked", allItems.value!!.itemLists[0].item_name)
//                }
//
//            })
//        }

//        viewModelScope.launch(Dispatchers.IO) {
//            val a = itemRepository.getAllItemsAsync().execute()
//            withContext(Dispatchers.Main) {
//                allItems.value = a.body()!!.itemLists
//            }
//
//        }

        //responseBody is null

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<ItemList> = itemRepository.getAllItemsAsync().await()
            val responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main) {
                    allItems.value = responseBody.itemLists
                }
            }

        }



        return allItems

    }

    fun getItemById(id: Long): LiveData<Item> {
        val item: MutableLiveData<Item> = MutableLiveData()

//        viewModelScope.launch(Dispatchers.IO) {
//
//            val call = itemRepository.getItemByIdAsync(id)
//
//            call.enqueue(object: Callback<Item> {
//                override fun onFailure(call: Call<Item>, t: Throwable) {
//                    Log.d("isFailed", t.message)
//                }
//
//                override fun onResponse(call: Call<Item>, response: Response<Item>) {
//                    item.value = response.body()
//                }
//
//            })
//        }

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


}