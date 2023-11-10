package com.home.fooddelivery.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDeliveryDao {

    @Query("SELECT * FROM meal_categories ORDER BY name")
    fun getMealCategories(): LiveData<List<MealCategoryDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealCategories(categories: List<MealCategoryDbModel>)

    @Query("SELECT * FROM meals ORDER BY name")
    fun getAllMeals(): LiveData<List<MealDbModel>>

    @Query("SELECT * FROM meals WHERE category == :category LIMIT 1")
    fun getMealsByCategory(category: String): LiveData<List<MealDbModel>>

    @Query("SELECT * FROM meals WHERE id == :id LIMIT 1")
    fun getMealById(id: Int): LiveData<MealDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals: List<MealDbModel>)
}