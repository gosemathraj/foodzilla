package com.gosemathraj.foodzilla.data.repo

import com.gosemathraj.foodzilla.data.local.LocalDataSource
import com.gosemathraj.foodzilla.data.remote.api.RemoteDataSource
import javax.inject.Inject

class FoodRepository @Inject constructor(private val localDataSource: LocalDataSource,
                                        private val remoteDataSource: RemoteDataSource) {

    
}