package com.zainco.dtag.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zainco.dtag.data.AuthRepository

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(authRepository) as T
    }
}