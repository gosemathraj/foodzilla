package com.gosemathraj.foodzilla.data.local.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gosemathraj.foodzilla.data.remote.models.Food

interface FoodzillaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foodList : List<Food>)

    @Query("SELECT * FROM food")
    suspend fun getAll(): List<Food>
}