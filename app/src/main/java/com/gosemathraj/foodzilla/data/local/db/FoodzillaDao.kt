package com.gosemathraj.foodzilla.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gosemathraj.foodzilla.data.local.entity.FoodEntity

@Dao
interface FoodzillaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foodList : List<FoodEntity>)

    @Query("SELECT * FROM foodentity")
    suspend fun getAll(): List<FoodEntity>
}