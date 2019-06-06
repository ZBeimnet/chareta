package com.example.webapitest.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceBuilder {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <S> buildService(serviceType: Class<S>): S {
            return retrofit.create(serviceType)

        }

    }


}