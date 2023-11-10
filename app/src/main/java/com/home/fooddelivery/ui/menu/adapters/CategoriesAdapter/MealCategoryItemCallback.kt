package com.home.fooddelivery.ui.menu.adapters.CategoriesAdapter

import androidx.recyclerview.widget.DiffUtil
import com.home.fooddelivery.domain.MealCategoryItem

class MealCategoryItemCallback: DiffUtil.ItemCallback<MealCategoryItem>() {


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
    override fun areItemsTheSame(oldItem: MealCategoryItem, newItem: MealCategoryItem): Boolean {
        return oldItem.enabled == newItem.enabled
    }
    override fun areContentsTheSame(oldItem: MealCategoryItem, newItem: MealCategoryItem): Boolean {
        return oldItem == newItem
    }
}