package com.example.chareta.data.remote.webservice

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ScalarService {

    //This Service Class is only to handle relationships between models

    @Headers("Content-Type: text/uri-list")
    @PUT("items/{id}/user")
    fun addBelongingToItemAsync(@Body uri: String, @Path("id") itemId: Long): Deferred<Response<Void>>
}

