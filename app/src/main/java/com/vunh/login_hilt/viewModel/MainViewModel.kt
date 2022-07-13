package com.vunh.login_hilt.viewModel

import androidx.lifecycle.ViewModel
import com.vunh.login_hilt.repository.login.LoginRepositoryImpl
import com.vunh.login_hilt.users.UserManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainViewModel (
    private val loginRepositoryImp: LoginRepositoryImpl,
    private val userManager: UserManager
) : ViewModel() , CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun logout() {
        userManager.logout()
    }
}