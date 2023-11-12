package com.home.fooddelivery.domain

class UpdateActiveCategoryUseCase(val repository: MealRepository) {

    fun updateActiveCategory(mealCategoryName: String): Int {
        return repository.updateActiveCategory(mealCategoryName)
    }
}