package com.zainco.dtag.presentation.auth

interface AuthListener {
    fun onSuccess()
    fun onFailure(message: String)
}