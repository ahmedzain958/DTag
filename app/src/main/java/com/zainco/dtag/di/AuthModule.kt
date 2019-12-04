package com.zainco.dtag.di

import com.zainco.dtag.data.auth.AuthRepository
import com.zainco.dtag.data.auth.AuthRepositoryImpl
import com.zainco.dtag.data.auth.FirebaseAuthDataSource
import com.zainco.dtag.data.auth.FirebaseAuthDataSourceImpl
import com.zainco.dtag.presentation.auth.AuthViewModelFactory
import org.koin.dsl.module.module


@JvmField
val authModule = module {
    factory<FirebaseAuthDataSource> {
        FirebaseAuthDataSourceImpl(
            get()
        )
    }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory { AuthViewModelFactory(get()) }
}
