package com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.fooddelivery.R
import com.home.fooddelivery.databinding.ItemPageBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

    private val res = arrayOf(R.drawable.view_page1, R.drawable.view_page2, R.drawable.view_page3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageBinding.inflate(inflater, parent, false)

        return PagerVH(binding)
    }


    override fun getItemCount(): Int = res.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        with(holder) {
            binding.backgroundImage.setImageResource(res[position])
        }
    }

    inner class PagerVH(val binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root)
}

