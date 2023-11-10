package com.home.fooddelivery.domain

class UpdateActiveCategoryUseCase(val repository: MealRepository) {

    fun updateActiveCategory(mealCategoryName: String) {
        repository.updateActiveCategory(mealCategoryName)
    }
}