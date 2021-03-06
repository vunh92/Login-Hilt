package com.vunh.login_hilt.api

import com.vunh.login_hilt.model.Account
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("pda/account/login")
    fun callLoginAsync(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Deferred<Account>
}