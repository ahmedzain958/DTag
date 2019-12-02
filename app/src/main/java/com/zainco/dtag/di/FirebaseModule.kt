package com.zainco.dtag.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module.module


@JvmField
val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}
