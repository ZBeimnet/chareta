package com.example.chareta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.Bid
import com.example.chareta.data.model.BidsWrapper
import com.example.chareta.repository.BidRepository
import com.example.chareta.data.remote.webservice.BidService
import com.example.chareta.data.remote.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BidViewModel(application: Application): AndroidViewModel(application) {

    private val bidRepository: BidRepository

    init {
        val bidService = ServiceBuilder.buildService(BidService::class.java)
        val bidDao = CharetaDatabase.getDatabase(application).bidDao()
        bidRepository = BidRepository(bidService, bidDao)
    }

    private  val _getResponse = MutableLiveData<Response<Bid>>()
    val getResponse: LiveData<Response<Bid>>
        get() = _getResponse

    private val _getResponses = MutableLiveData<Response<BidsWrapper>>()
    val getResponses: LiveData<Response<BidsWrapper>>
        get() = _getResponses

    private val _getRelatedResponses = MutableLiveData<Response<List<Bid>>>()
    val getRelatedResponses: LiveData<Response<List<Bid>>>
        get() = _getRelatedResponses

    private val _updateResponse = MutableLiveData<Response<Bid>>()
    val updateResponse: LiveData<Response<Bid>>
        get() = _updateResponse

    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse: LiveData<Response<Void>>
        get() = _insertResponse

    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse: MutableLiveData<Response<Void>>
        get() = _deleteResponse

    fun getBids() = viewModelScope.launch {
        _getResponses.postValue(bidRepository.getBids())
    }

    fun getBidById(id: Long) = viewModelScope.launch{
        _getResponse.postValue(bidRepository.getBidById(id))
    }

    fun getBidsByItemId(itemId: Long) = viewModelScope.launch {
        _getRelatedResponses.postValue(bidRepository.getBidsByItemId(itemId))
    }

    fun insertBid(bid: Bid) = viewModelScope.launch {
        _insertResponse.postValue(bidRepository.insertBid(bid))
    }

    fun updateItem(id: Long, bid: Bid) = viewModelScope.launch {
        _updateResponse.postValue(bidRepository.updateItem(id, bid))
    }

    fun deleteItem(id: Long) = viewModelScope.launch {
        _deleteResponse.postValue(bidRepository.deleteItem(id))
    }

    fun getBidsFromLocal(): LiveData<List<Bid>> {
        lateinit var bids: LiveData<List<Bid>>
        viewModelScope.launch {
            val allBids = bidRepository.getBidsFromLocal()
            withContext(Dispatchers.Main) {
                bids = allBids
            }
        }
        return bids
    }

}