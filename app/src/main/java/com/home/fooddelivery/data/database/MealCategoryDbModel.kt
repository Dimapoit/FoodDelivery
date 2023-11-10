package com.home.fooddelivery.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_categories")
data class MealCategoryDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val enabled: Boolean = false
)
