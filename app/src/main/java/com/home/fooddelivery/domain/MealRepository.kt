package com.home.fooddelivery.domain

import androidx.lifecycle.LiveData

interface MealRepository {


    suspend fun loadData()
    fun getMealPositionByCategory(categoryItem: MealCategoryItem): Int
    suspend fun getMealById(id: Int): MealItem
    fun getCategoriesList(): LiveData<List<MealCategoryItem>>
    fun getMealsList(): LiveData<List<MealItem>>
    fun updateActiveCategory(mealCategoryName: String)
}