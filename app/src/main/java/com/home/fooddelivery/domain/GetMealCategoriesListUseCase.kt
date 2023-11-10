package com.home.fooddelivery.domain

class GetMealCategoriesListUseCase(
    private val repository: MealRepository
) {
    operator fun invoke() = repository.getCategoriesList()
}