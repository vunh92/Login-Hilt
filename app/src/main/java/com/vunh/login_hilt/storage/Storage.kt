package com.vunh.login_hilt.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}