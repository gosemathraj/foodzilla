package com.gosemathraj.foodzilla.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodEntity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "image") val image : String?,
    @ColumnInfo(name = "filter") val filter : String?
)