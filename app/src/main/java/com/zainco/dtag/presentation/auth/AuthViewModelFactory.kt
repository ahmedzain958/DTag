package com.zainco.dtag.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zainco.dtag.data.auth.AuthRepository

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(authRepository) as T
    }
}