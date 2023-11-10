package com.home.fooddelivery.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealsListDto(
    @SerializedName("meals")
    @Expose
    val meals: List<MealDto>? = null
)
