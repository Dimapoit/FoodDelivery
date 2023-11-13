package com.home.fooddelivery.ui.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.fooddelivery.domain.GetMealCategoriesListUseCase
import com.home.fooddelivery.domain.GetMealPositionByCategoryUceCase
import com.home.fooddelivery.domain.GetMealsListUseCase
import com.home.fooddelivery.domain.LoadDataUseCase
import com.home.fooddelivery.domain.MealCategoryItem
import com.home.fooddelivery.domain.UpdateActiveCategoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getMealCategoriesListUseCase: GetMealCategoriesListUseCase,
    private val getMealsListUseCase: GetMealsListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val getMealPositionByCategoryUceCase: GetMealPositionByCategoryUceCase,
    private val updateActiveCategoryUseCase: UpdateActiveCategoryUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            loadDataUseCase.invoke()
            Log.d("loadData", "init")
        }
    }

    val mealCategoriesList = getMealCategoriesListUseCase.getMealCategoriesList()
    val mealsList = getMealsListUseCase.invoke()

    fun getMealsPositionByCategory(mealCategoryItem: MealCategoryItem): Int {

        return getMealPositionByCategoryUceCase.getMealPositionByCategory(mealCategoryItem)
    }

    fun updateActiveCategory(mealCategoryName: String): Int {
        return updateActiveCategoryUseCase.updateActiveCategory(mealCategoryName)
    }
}

