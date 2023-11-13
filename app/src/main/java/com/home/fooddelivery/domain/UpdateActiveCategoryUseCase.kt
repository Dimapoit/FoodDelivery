package com.home.fooddelivery.domain

import javax.inject.Inject

class UpdateActiveCategoryUseCase @Inject constructor(
    private val repository: MealRepository
) {

    fun updateActiveCategory(mealCategoryName: String): Int {
        return repository.updateActiveCategory(mealCategoryName)
    }
}