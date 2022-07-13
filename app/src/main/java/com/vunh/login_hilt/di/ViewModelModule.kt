package com.vunh.login_hilt.di

import com.vunh.login_hilt.repository.login.LoginRepositoryImpl
import com.vunh.login_hilt.users.UserManager
import com.vunh.login_hilt.viewModel.LoginViewModelFactory
import com.vunh.login_hilt.viewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
class ViewModelModule {
    @Provides
    fun providesLoginViewModelFactory(
        loginRepositoryImp: LoginRepositoryImpl,
        userManager: UserManager,
    ) = LoginViewModelFactory(loginRepositoryImp, userManager)

    @Provides
    fun providesMainViewModelFactory(
        loginRepositoryImp: LoginRepositoryImpl,
        userManager: UserManager,
    ) = MainViewModelFactory(loginRepositoryImp, userManager)
}