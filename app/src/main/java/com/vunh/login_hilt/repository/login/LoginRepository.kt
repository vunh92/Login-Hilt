package com.vunh.login_hilt.repository.login

import com.vunh.login_hilt.model.Account
import com.vunh.login_hilt.usecase.UseCaseResult


interface LoginRepository {
    suspend fun getUser(username: String, password: String): UseCaseResult<Account>
}