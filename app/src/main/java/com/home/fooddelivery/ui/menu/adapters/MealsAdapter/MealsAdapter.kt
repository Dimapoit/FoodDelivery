package com.home.fooddelivery.ui.menu.adapters.MealsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.home.fooddelivery.R
import com.home.fooddelivery.databinding.ItemMealBinding
import com.home.fooddelivery.domain.MealItem
import com.squareup.picasso.Picasso

class MealsAdapter: ListAdapter<MealItem, MealsVH>(MealItemCallback()) {

    var onMealItemClick: ((MealItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsVH {
        val binding = ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealsVH(binding)
    }

    override fun onBindViewHolder(holder: MealsVH, position: Int) {
        val mealItem = getItem(position)

        with(holder.binding) {

            titleTv.text = mealItem.name
            descriptionTv.text = mealItem.category

            Picasso.get().load(mealItem.imagePath)
                .placeholder(R.drawable.placeholder)
                .into(imageView)
            root.setOnClickListener {
                onMealItemClick?.invoke(mealItem)
            }
        }
    }
}