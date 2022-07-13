package com.vunh.login_hilt.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vunh.login_hilt.repository.login.LoginRepositoryImpl
import com.vunh.login_hilt.users.UserManager
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val loginRepositoryImp: LoginRepositoryImpl,
    private val userManager: UserManager,
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(loginRepositoryImp, userManager) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}