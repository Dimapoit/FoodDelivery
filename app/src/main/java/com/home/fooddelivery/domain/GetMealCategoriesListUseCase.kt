package com.home.fooddelivery.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetMealCategoriesListUseCase @Inject constructor(
    private val repository: MealRepository
) {
    fun getMealCategoriesList(): LiveData<List<MealCategoryItem>> {
        return repository.getCategoriesList()
    }
}