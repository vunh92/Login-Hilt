package com.vunh.login_hilt.di

import com.vunh.login_hilt.storage.SharedPreferencesStorage
import com.vunh.login_hilt.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}