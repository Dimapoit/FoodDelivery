package com.home.fooddelivery.ui.menu.adapters.MealsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.home.fooddelivery.domain.MealItem

class MealItemCallback : DiffUtil.ItemCallback<MealItem>() {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
    override fun areItemsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
        return oldItem == newItem
    }
}