package com.home.fooddelivery.ui.menu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.home.fooddelivery.R
import com.home.fooddelivery.databinding.FragmentMenuBinding
import com.home.fooddelivery.ui.MealApplication
import com.home.fooddelivery.ui.ViewModelFactory
import com.home.fooddelivery.ui.menu.adapters.CategoriesAdapter.MealCategoriesAdapter
import com.home.fooddelivery.ui.menu.adapters.MealsAdapter.MealsAdapter
import com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter.ViewPagerAdapter
import javax.inject.Inject

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentMenuBinding == null")

    private val component by lazy {
        (requireActivity().application as MealApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private lateinit var menuViewModel: MenuViewModel

    lateinit var mealCategoriesAdapter: MealCategoriesAdapter
    lateinit var mealsAdapter: MealsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuViewModel =
            ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]

        initViewPager()
        initCategoriesRW()
        initMealsRW()

        menuViewModel.mealCategoriesList.observe(viewLifecycleOwner) {
            mealCategoriesAdapter.submitList(it)
        }
        menuViewModel.mealsList.observe(viewLifecycleOwner) {
            mealsAdapter.submitList(it)
        }

    }

    private fun initViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter()
        with(binding) {
            viewPager.setPageTransformer { page, position ->
                val pageWidth = page.width
                val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin)
                val offset = position * -(2 * pageMargin + pageWidth)

                if (position < -1) {
                    page.translationX = 0f
                } else if (position <= 1) {
                    if (position >= -1 && position < 0) {
                        page.translationX = -offset / 2
                    } else {
                        page.translationX = offset / 16
                    }
                } else {
                    page.translationX = 0f
                }
            }
            viewPager.offscreenPageLimit = 3
        }
    }

    private fun initCategoriesRW() {
        mealCategoriesAdapter = MealCategoriesAdapter()
        binding.rvCategoryList.adapter = mealCategoriesAdapter
        binding.rvCategoryList.itemAnimator = null
        binding.rvCategoryList.recycledViewPool.setMaxRecycledViews(
            MealCategoriesAdapter.VIEW_TYPE_ENABLED,
            MealCategoriesAdapter.MAX_POOL_SIZE
        )
        binding.rvCategoryList.recycledViewPool.setMaxRecycledViews(
            MealCategoriesAdapter.VIEW_TYPE_DISABLED,
            MealCategoriesAdapter.MAX_POOL_SIZE
        )
        initCategoryClickListener()
    }

    private fun initMealsRW() {

        mealsAdapter = MealsAdapter()
        binding.rvMealsList.adapter = mealsAdapter
        binding.rvMealsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.rvMealsList.layoutManager as LinearLayoutManager
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                //val visibleItemCount = layoutManager.childCount
                //val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                //val totalItemCount = layoutManager.itemCount
                val meal = mealsAdapter.currentList[firstVisibleItem].copy()

                updateActiveCategory(meal.category)
            }
        })
        initMealClickListener()
    }

    private fun initCategoryClickListener() {

        mealCategoriesAdapter.onCategoryItemClick = { mealCategory ->

            val mealPosition = menuViewModel.getMealsPositionByCategory(mealCategory) + 1
            val layoutManager = binding.rvMealsList.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val offset = mealPosition - firstVisibleItemPosition
            layoutManager.scrollToPositionWithOffset(mealPosition, offset)
            updateActiveCategory(mealCategory.name)
        }
    }

    private fun updateActiveCategory(activeCategoryName: String) {

        // Обновление активной категории
        val activeCategoryPosition = menuViewModel.updateActiveCategory(activeCategoryName)

        // Проверяем видимость активной категории и прокручиваем, если она не видна
        val layoutManager = binding.rvCategoryList.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        if (activeCategoryPosition < firstVisibleItemPosition || activeCategoryPosition > lastVisibleItemPosition) {

            binding.rvCategoryList.scrollToPosition(activeCategoryPosition)
        }
    }

    private fun initMealClickListener() {

        mealsAdapter.onMealItemClick = {

            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


