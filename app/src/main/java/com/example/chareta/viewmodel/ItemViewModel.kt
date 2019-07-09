package com.example.chareta.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.NavigationHost
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.Item
import com.example.chareta.data.model.ItemsWrapper
import com.example.chareta.repository.ItemRepository
import com.example.chareta.data.remote.webservice.ItemService
import com.example.chareta.data.remote.ServiceBuilder
import com.example.chareta.view.PostedItemFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ItemViewModel(application: Application): AndroidViewModel(application) {



    private val itemRepository: ItemRepository

    init {
        val itemService = ServiceBuilder.buildService(ItemService::class.java)
        val itemDao = CharetaDatabase.getDatabase(application).itemDao()
        itemRepository = ItemRepository(itemService, itemDao)
    }
    @Bindable
    val itemName = MutableLiveData<String>()

    @Bindable
    val itemStartingPrice = MutableLiveData<String>()

    @Bindable
    val itemDescription = MutableLiveData<String>()

    @Bindable
    val expiryDate = MutableLiveData<String>()


    fun onPickDateButtonClicked(){
        val c = java.util.Calendar.getInstance()
        val year = c.get(java.util.Calendar.YEAR)
        val month = c.get(java.util.Calendar.MONTH)
        val day = c.get(java.util.Calendar.DAY_OF_MONTH)
        val dpd =
            DatePickerDialog(getApplication(), DatePickerDialog.OnDateSetListener { view, myear, mmonth, mday ->
                expiryDate

            }, year, month, day)
        dpd.show()

    }

    fun onPostButtonClicked(){

            insertItem(Item(0,itemName.toString(),itemDescription.toString(),itemStartingPrice.toString().toLong(),java.util.Date().toString(),expiryDate.toString()))

            Toast.makeText(getApplication(), "Post added", Toast.LENGTH_LONG).show()

    }
    fun onBackButtonClicked(){

    }





    private  val _getResponse = MutableLiveData<Response<Item>>()
    val getResponse: LiveData<Response<Item>>
        get() = _getResponse

    private val _getResponses = MutableLiveData<Response<ItemsWrapper>>()
    val getResponses: LiveData<Response<ItemsWrapper>>
        get() = _getResponses

//    private val _getRelatedResponses = MutableLiveData<Response<List<Item>>>()
//    val getRelatedResponses: LiveData<Response<List<Item>>>
//        get() = _getRelatedResponses

    private val _updateResponse = MutableLiveData<Response<Item>>()
    val updateResponse: LiveData<Response<Item>>
        get() = _updateResponse

    private val _insertResponse = MutableLiveData<Response<Item>>()
    val insertResponse: LiveData<Response<Item>>
        get() = _insertResponse

    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse: MutableLiveData<Response<Void>>
        get() = _deleteResponse


    fun getItems() = viewModelScope.launch {
        _getResponses.postValue(itemRepository.getItems())
    }

    fun getItemById(id: Long) = viewModelScope.launch{
        _getResponse.postValue(itemRepository.getItemById(id))
    }

    fun getItemsByUserId(userId: Long) = viewModelScope.launch {
        _getResponses.postValue(itemRepository.getItemsByUserId(userId))
    }

    fun insertItem(item: Item) = viewModelScope.launch {
        _insertResponse.postValue(itemRepository.insertItem(item))
    }

    fun updateItem(id: Long, item: Item) = viewModelScope.launch {
        _updateResponse.postValue(itemRepository.updateItem(id, item))
    }

    fun deleteItem(id: Long) = viewModelScope.launch {
        _deleteResponse.postValue(itemRepository.deleteItem(id))
    }

    fun getItemsFromLocal(): LiveData<List<Item>> {
        val items: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
        viewModelScope.launch {
            val allItems = itemRepository.getItemsFromLocal()
            withContext(Dispatchers.Main) {
                items.value = allItems.value
            }
        }

        return items
    }

}