package com.home.fooddelivery.mapper

import com.home.fooddelivery.data.database.MealCategoryDbModel
import com.home.fooddelivery.data.network.models.MealCategoryDto
import com.home.fooddelivery.data.network.models.MealDto
import com.home.fooddelivery.domain.MealCategoryItem
import com.home.fooddelivery.domain.MealItem
import javax.inject.Inject

class MealMapper @Inject constructor() {

    fun mapCategoryDtoToEntity(mealCategoryDto: MealCategoryDto): MealCategoryItem {
        return MealCategoryItem(
            id = mealCategoryDto.idCategory?.toInt() ?: 0,
            name = mealCategoryDto.strCategory ?: ""
        )
    }

    fun mapCategoryDtoToDbModel(mealCategoryDto: MealCategoryDto): MealCategoryDbModel {
        return MealCategoryDbModel(
            id = mealCategoryDto.idCategory?.toInt() ?: 0,
            name = mealCategoryDto.strCategory ?: ""
        )
    }

    fun mealDtoToEntity(mealDto: MealDto): MealItem {
        return MealItem(
            id = mealDto.idMeal?.toInt() ?: 0,
            name = mealDto.strMeal ?: "",
            category = mealDto.strCategory ?: "",
            instructions = ("With the idea of imparting programming\n" +
                    "      knowledge, Mr. Sandeep Jain, an IIT Roorkee alumnus started a\n" +
                    "      dream, GeeksforGeeks. Whether programming excites you or you feel\n" +
                    "      stifled, wondering how to prepare for interview questions or how to\n" +
                    "      ace data structures and algorithms, GeeksforGeeks is a one-stop solution."),
            imagePath = mealDto.strMealThumb ?: ""
            )
    }
}