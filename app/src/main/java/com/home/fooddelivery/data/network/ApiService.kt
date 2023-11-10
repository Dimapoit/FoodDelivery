package com.home.fooddelivery.data.network

import com.home.fooddelivery.data.network.models.MealsCategoryList
import com.home.fooddelivery.data.network.models.MealsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getAllMealCategories(
    ): MealsCategoryList

    @GET("filter.php?")
    suspend fun getMealsByCategory(
        @Query(QUERY_PARAM_CATEGORY_NAME) categoryName: String
    ): MealsListDto

    @GET("lookup.php?i=52772")
    suspend fun getMealById(
        @Query(QUERY_PARAM_MEAL_ID) mealId: Int
    ): MealsListDto

    companion object {
        private const val QUERY_PARAM_CATEGORY_NAME = "c"

        private const val QUERY_PARAM_MEAL_ID = "meal_id"
    }
}