package com.example.chareta.repository

import android.util.Log
import com.example.chareta.data.remote.webservice.ScalarService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class ScalarRepository {

    private val scalarService: ScalarService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        scalarService = retrofit.create(ScalarService::class.java)
    }

    fun addBelongingToItem(uri: String, itemId: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> = scalarService.addBelongingToItem(uri, itemId).await()
            Log.d("relationship_added", response.message())
        }
    }

}