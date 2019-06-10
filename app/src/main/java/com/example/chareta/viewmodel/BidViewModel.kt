package com.example.chareta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chareta.data.remote.model.Bid
import com.example.chareta.repository.BidRepository
import com.example.chareta.data.remote.webservice.BidService
import com.example.chareta.data.remote.webservice.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BidViewModel(application: Application): AndroidViewModel(application) {

    private val bidRepository: BidRepository

    init {
        val bidService = ServiceBuilder.buildService(BidService::class.java)
        bidRepository = BidRepository(bidService)
    }

    fun getBidsByItemId(id: Long): LiveData<List<Bid>> {
        val bids: MutableLiveData<List<Bid>> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<List<Bid>> = bidRepository.getBidsByItemIdAsync(id).await()
            val responseBody = response.body()
            if(responseBody != null) {
                withContext(Dispatchers.Main) {
                    bids.value = responseBody
                }
            }
        }

        return bids
    }
}