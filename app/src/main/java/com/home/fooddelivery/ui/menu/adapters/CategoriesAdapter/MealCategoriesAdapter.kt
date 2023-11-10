package com.home.fooddelivery.ui.menu.adapters.CategoriesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home.fooddelivery.databinding.ItemCategoryActiveBinding
import com.home.fooddelivery.databinding.ItemCategoryBinding
import com.home.fooddelivery.domain.MealCategoryItem

class MealCategoriesAdapter :
    ListAdapter<MealCategoryItem, RecyclerView.ViewHolder>(MealCategoryItemCallback()) {

    var onCategoryItemClick: ((MealCategoryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_ENABLED -> {
                val binding = ItemCategoryActiveBinding.inflate(inflater, parent, false)
                MealActiveCategoryItemVH(binding)
            }

            VIEW_TYPE_DISABLED -> {
                val binding = ItemCategoryBinding.inflate(inflater, parent, false)
                MealCategoryItemVH(binding)
            }

            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val categoryItem = getItem(position)
        when (holder) {
            is MealActiveCategoryItemVH -> {
                val binding = holder.binding
                binding.categoryName.text = categoryItem.name
                binding.root.setOnClickListener {
                    onCategoryItemClick?.invoke(categoryItem)
                }
            }
            is MealCategoryItemVH -> {
                val binding = holder.binding
                binding.categoryName.text = categoryItem.name
                binding.root.setOnClickListener {
                    onCategoryItemClick?.invoke(categoryItem)
                }
            }
        }

    }
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 200
        const val MAX_POOL_SIZE = 10
    }

}