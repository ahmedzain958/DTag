package com.zainco.dtag.di

import com.zainco.dtag.data.AuthRepository
import com.zainco.dtag.data.AuthRepositoryImpl
import com.zainco.dtag.data.FirebaseAuthDataSource
import com.zainco.dtag.ui.auth.AuthViewModelFactory
import org.koin.dsl.module.module


@JvmField
val authModule = module {
    factory { FirebaseAuthDataSource(get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory { AuthViewModelFactory(get()) }
}
