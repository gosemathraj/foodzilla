package com.gosemathraj.foodzilla.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gosemathraj.foodzilla.data.local.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
abstract class FoodzillaDatabase : RoomDatabase() {

    abstract fun foodzillaDao(): FoodzillaDao
}