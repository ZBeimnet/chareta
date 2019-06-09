package com.example.chareta.webservice

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {

    companion object {
        private val retrofit = Retrofit.Builder()
<<<<<<< HEAD
            .baseUrl("http://10.0.2.2:8080/")
=======
            .baseUrl("http://192.168.8.102:8080/")
>>>>>>> 3ff455dbe7c1bf2e00ec2081659a1bef181f0d92
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        fun <S> buildService(serviceType: Class<S>): S {
            return retrofit.create(serviceType)
        }
    }
}