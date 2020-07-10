package com.gosemathraj.foodzilla.data.local

import com.gosemathraj.foodzilla.data.remote.models.Food

interface FoodzillaDBContract {

    suspend fun getAll(): List<Food>

    suspend fun insertAll(foodList: List<Food>)
}