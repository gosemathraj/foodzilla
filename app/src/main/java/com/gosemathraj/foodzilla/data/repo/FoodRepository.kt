package com.gosemathraj.foodzilla.data.repo

import com.gosemathraj.foodzilla.data.local.LocalDataSource
import com.gosemathraj.foodzilla.data.remote.api.RemoteDataSource
import com.gosemathraj.foodzilla.data.remote.dto.FoodDTO
import javax.inject.Inject

class FoodRepository @Inject constructor(private val localDataSource: LocalDataSource,
                                        private val remoteDataSource: RemoteDataSource) {

    suspend fun getFoodList() : List<FoodDTO>? = remoteDataSource.getCollection()
}