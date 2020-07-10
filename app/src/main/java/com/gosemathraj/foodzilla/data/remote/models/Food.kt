package com.gosemathraj.foodzilla.data.remote.models

import com.google.gson.annotations.SerializedName

data class Food (
    @field:SerializedName("image") val image : String?,
    @field:SerializedName("filter") val filter : String?,
    @field:SerializedName("title") val title : String?
)