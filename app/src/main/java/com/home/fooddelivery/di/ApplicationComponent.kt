package com.home.fooddelivery.di

import android.app.Application
import com.home.fooddelivery.MainActivity
import com.home.fooddelivery.ui.cart.CartFragment
import com.home.fooddelivery.ui.menu.MenuFragment
import com.home.fooddelivery.ui.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: CartFragment)
    fun inject(fragment: ProfileFragment)


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}