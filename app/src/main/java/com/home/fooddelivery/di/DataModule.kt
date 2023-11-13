package com.home.fooddelivery.di

import android.app.Application
import com.home.fooddelivery.data.database.AppDatabase
import com.home.fooddelivery.data.database.FoodDeliveryDao
import com.home.fooddelivery.data.network.ApiFactory
import com.home.fooddelivery.data.network.ApiService
import com.home.fooddelivery.data.repository.MealRepositoryImpl
import com.home.fooddelivery.domain.MealRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindMealRepository(impl: MealRepositoryImpl): MealRepository

    companion object {

        @Provides
        fun foodDeliveryDao(
            application: Application
        ): FoodDeliveryDao {
            return AppDatabase.getInstance(application).foodDeliveryDao()
        }

        @Provides
        fun apiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}