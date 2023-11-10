package com.home.fooddelivery.domain

class GetMealsListUseCase(
    private val repository: MealRepository
) {
    operator fun invoke() = repository.getMealsList()
}