package com.gosemathraj.foodzilla.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FoodDTO (
    @field:SerializedName("image") val image : String?,
    @field:SerializedName("filter") val filter : String?,
    @field:SerializedName("title") val title : String?
)