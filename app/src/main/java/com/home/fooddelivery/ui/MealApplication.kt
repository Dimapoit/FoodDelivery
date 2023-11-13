package com.home.fooddelivery.ui

import android.app.Application
import com.home.fooddelivery.di.DaggerApplicationComponent

class MealApplication: Application() {

val component by lazy {
    DaggerApplicationComponent.factory().create(this)
}
}