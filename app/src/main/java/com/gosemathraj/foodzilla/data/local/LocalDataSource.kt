package com.gosemathraj.foodzilla.data.local

import com.gosemathraj.foodzilla.data.local.db.FoodzillaDatabase
import com.gosemathraj.foodzilla.data.local.entity.FoodEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val foodzillaDatabase: FoodzillaDatabase) : FoodzillaDBContract {

    override suspend fun getAll(): List<FoodEntity> = foodzillaDatabase.foodzillaDao().getAll()

    override suspend fun insertAll(foodList: List<FoodEntity>) = foodzillaDatabase.foodzillaDao().insertAll(foodList)
}