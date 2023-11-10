package com.home.fooddelivery.domain

class GetMealPositionByCategoryUceCase(
    private val repository: MealRepository
) {
    fun getMealPositionByCategory(categoryItem: MealCategoryItem): Int {
        return repository.getMealPositionByCategory(categoryItem)
    }
}