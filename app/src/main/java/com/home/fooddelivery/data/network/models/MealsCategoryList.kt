package com.home.fooddelivery.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealsCategoryList(
    @SerializedName("categories")
    @Expose
    val categories: List<MealCategoryDto>? = null
)
