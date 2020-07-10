package com.gosemathraj.foodzilla.data.local

import com.gosemathraj.foodzilla.data.local.db.FoodzillaDatabase
import com.gosemathraj.foodzilla.data.remote.models.Food
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val foodzillaDatabase: FoodzillaDatabase) : FoodzillaDBContract {

    override suspend fun getAll(): List<Food> = foodzillaDatabase.foodzillaDao().getAll()

    override suspend fun insertAll(foodList: List<Food>) = foodzillaDatabase.foodzillaDao().insertAll(foodList)
}