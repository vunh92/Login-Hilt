package com.vunh.login_hilt.di

import android.content.Context
import com.vunh.login_hilt.users.UserManager
import com.vunh.login_hilt.view.LoginActivity
import com.vunh.login_hilt.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetworkModule::class, ApiModule::class, ViewModelModule::class, StorageModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
    fun userManager(): UserManager
}