package com.home.fooddelivery.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val category: String,
    val imagePath: String
)
