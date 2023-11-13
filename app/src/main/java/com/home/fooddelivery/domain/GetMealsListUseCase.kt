package com.home.fooddelivery.domain

import javax.inject.Inject

class GetMealsListUseCase @Inject constructor(
    private val repository: MealRepository
) {
    operator fun invoke() = repository.getMealsList()
}