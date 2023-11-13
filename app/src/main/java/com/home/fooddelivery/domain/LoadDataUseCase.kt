package com.home.fooddelivery.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke() = repository.loadData()
}