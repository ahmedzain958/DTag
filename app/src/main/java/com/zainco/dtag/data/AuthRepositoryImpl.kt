package com.zainco.dtag.data

import com.zainco.dtag.data.AuthRepository
import com.zainco.dtag.data.FirebaseAuthDataSource

class AuthRepositoryImpl(private val firebase: FirebaseAuthDataSource) :
    AuthRepository {
    override fun login(email: String, password: String) = firebase.login(email, password)

    override fun register(email: String, password: String) = firebase.register(email, password)

    override fun currentUser() = firebase.currentUser()

    override fun logout() = firebase.logout()
}