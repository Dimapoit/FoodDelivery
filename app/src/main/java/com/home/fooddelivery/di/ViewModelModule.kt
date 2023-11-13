package com.home.fooddelivery.di

import androidx.lifecycle.ViewModel
import com.home.fooddelivery.ui.cart.CartViewModel
import com.home.fooddelivery.ui.menu.MenuViewModel
import com.home.fooddelivery.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel
}