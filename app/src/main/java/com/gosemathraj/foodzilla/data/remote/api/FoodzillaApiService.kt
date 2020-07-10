package com.reconnect.reconnectapp.data.remote.api

import com.gosemathraj.foodzilla.data.remote.dto.FoodDTO
import retrofit2.http.GET

interface FoodzillaApiService {

    @GET(Urls.getCollection)
    suspend fun getCollection() : List<FoodDTO>?
}