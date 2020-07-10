package com.gosemathraj.foodzilla.data.local

import com.gosemathraj.foodzilla.data.local.entity.FoodEntity

interface FoodzillaDBContract {

    suspend fun getAll(): List<FoodEntity>

    suspend fun insertAll(foodList: List<FoodEntity>)
}