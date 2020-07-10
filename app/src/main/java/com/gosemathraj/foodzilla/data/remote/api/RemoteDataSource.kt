package com.gosemathraj.foodzilla.data.remote.api

import com.reconnect.reconnectapp.data.remote.api.FoodzillaApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodzillaApiService: FoodzillaApiService) {

    suspend fun getCollection() = foodzillaApiService.getCollection()
}