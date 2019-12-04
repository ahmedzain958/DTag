package com.zainco.dtag.ui.auth

interface AuthListener {
    fun onSuccess()
    fun onFailure(message: String)
}