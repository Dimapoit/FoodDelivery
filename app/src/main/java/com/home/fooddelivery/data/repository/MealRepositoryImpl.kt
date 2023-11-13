package com.home.fooddelivery.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home.fooddelivery.data.network.ApiService
import com.home.fooddelivery.domain.MealCategoryItem
import com.home.fooddelivery.domain.MealItem
import com.home.fooddelivery.domain.MealRepository
import com.home.fooddelivery.mapper.MealMapper
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mapper: MealMapper,
    private val apiService: ApiService
) : MealRepository {

    private val categoriesListLD = MutableLiveData<List<MealCategoryItem>>()
    private var categoriesList = mutableListOf<MealCategoryItem>()

    private val mealsListLD = MutableLiveData<List<MealItem>>()
    private var mealsList = mutableListOf<MealItem>()

    fun setCategoriesListLD() {
        categoriesListLD.postValue(categoriesList.toList())
    }

    fun setMealsListLD() {
        mealsListLD.postValue(mealsList.toList())
    }

    override suspend fun loadData() {
        val categories = apiService.getAllMealCategories().categories

        val list = categories?.map {

            mapper.mapCategoryDtoToEntity(it)
        }
        if (list != null) {
            list.sortedBy { it.name }
            categoriesList.addAll(list.sortedBy { it.name })
            val oldItem = categoriesList.first()
            val newItem = MealCategoryItem(id = oldItem.id, name = oldItem.name, true)
            categoriesList[0] = newItem
            for (i in categoriesList) {
                loadMealsByCategory(i)
            }
            Log.d("loadData", "categoriesListLD $categoriesListLD")
            setCategoriesListLD()
            setMealsListLD()
            Log.d("loadData", "mealsListLD $mealsListLD")
        }
    }

    suspend fun loadMealsByCategory(newItem: MealCategoryItem) {

        val meals = apiService.getMealsByCategory(newItem.name)
        val list = meals.meals?.map {

            mapper.mealDtoToEntity(it)
        }
        if (list != null) {
            for ((index, _) in list.withIndex()) {
                list[index].category = newItem.name
            }
            mealsList.addAll(list.toMutableList())
        }
    }

    override fun updateActiveCategory(mealCategoryName: String) : Int {

        val oldItem = categoriesList.find { it.enabled }
        val newItem = categoriesList.find { it.name == mealCategoryName }

        val newIndex = categoriesList.indexOf(newItem)

        if (oldItem != null && newItem != null && oldItem.name != mealCategoryName) {
            val oldIndex = categoriesList.indexOf(oldItem)
                categoriesList[oldIndex] =
                    MealCategoryItem(id = oldItem.id, name = oldItem.name, enabled = false)
                setCategoriesListLD()
                categoriesList[newIndex] =
                    MealCategoryItem(id = newItem.id, name = newItem.name, enabled = true)
                setCategoriesListLD()
        }

        return newIndex
    }

    override fun getMealPositionByCategory(newItem: MealCategoryItem): Int {

        val oldItem = categoriesList.find { it.enabled }

        val oldIndex = categoriesList.indexOf(oldItem)

        val newIndex = categoriesList.indexOf(newItem)

        if (oldItem != newItem) {
            oldItem?.let {
                categoriesList[oldIndex] =
                    MealCategoryItem(id = oldItem.id, name = oldItem.name, enabled = false)
                categoriesList[newIndex] =
                    MealCategoryItem(id = newItem.id, name = newItem.name, enabled = true)
                setCategoriesListLD()
            }
        }
        val categoryName = newItem.name
        val meal = mealsList.find { it.category == categoryName }
        return mealsList.indexOf(meal)
    }

    override suspend fun getMealById(id: Int): MealItem {
        TODO("Not yet implemented")
    }

    override fun getCategoriesList(): LiveData<List<MealCategoryItem>> {
        return categoriesListLD
    }

    override fun getMealsList(): LiveData<List<MealItem>> {
        return mealsListLD
    }
}