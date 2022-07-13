package com.vunh.login_hilt

import android.app.Application
import com.vunh.login_hilt.di.AppComponent
import com.vunh.login_hilt.di.DaggerAppComponent

class BaseApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}