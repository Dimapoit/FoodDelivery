package com.home.fooddelivery.domain

import javax.inject.Inject

class GetMealPositionByCategoryUceCase @Inject constructor(
    private val repository: MealRepository
) {
    fun getMealPositionByCategory(categoryItem: MealCategoryItem): Int {
        return repository.getMealPositionByCategory(categoryItem)
    }
}