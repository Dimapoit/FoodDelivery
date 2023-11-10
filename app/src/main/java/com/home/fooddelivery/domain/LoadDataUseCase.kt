package com.home.fooddelivery.domain

class LoadDataUseCase  (private val repository: MealRepository
) {
    suspend operator fun invoke() = repository.loadData()
}