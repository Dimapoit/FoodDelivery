package com.home.fooddelivery.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.fooddelivery.data.repository.MealRepositoryImpl
import com.home.fooddelivery.domain.GetMealCategoriesListUseCase
import com.home.fooddelivery.domain.GetMealsListUseCase
import com.home.fooddelivery.domain.LoadDataUseCase
import com.home.fooddelivery.domain.GetMealPositionByCategoryUceCase
import com.home.fooddelivery.domain.MealCategoryItem
import com.home.fooddelivery.domain.UpdateActiveCategoryUseCase
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {


    private val repository = MealRepositoryImpl()
    private val getMealCategoriesListUseCase = GetMealCategoriesListUseCase(repository)
    private val getMealsListUseCase = GetMealsListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getMealPositionByCategoryUceCase = GetMealPositionByCategoryUceCase(repository)
    private val updateActiveCategoryUseCase = UpdateActiveCategoryUseCase(repository)

    init {
        viewModelScope.launch {
            loadDataUseCase.invoke()
        }
    }

    val mealCategoriesList = getMealCategoriesListUseCase.invoke()
    val mealsList = getMealsListUseCase.invoke()

    fun getMealsPositionByCategory(mealCategoryItem: MealCategoryItem): Int {

        return  getMealPositionByCategoryUceCase.getMealPositionByCategory(mealCategoryItem)
    }

    fun updateActiveCategory(mealCategoryName: String) {
        updateActiveCategoryUseCase.updateActiveCategory((mealCategoryName))
    }
}

