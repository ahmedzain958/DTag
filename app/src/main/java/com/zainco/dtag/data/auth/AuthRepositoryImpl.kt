package com.zainco.dtag.data.auth

class AuthRepositoryImpl(private val firebaseAuthDataSource: FirebaseAuthDataSource) :
    AuthRepository {
    override fun login(email: String, password: String) =
        firebaseAuthDataSource.login(email, password)

    override fun register(email: String, password: String) =
        firebaseAuthDataSource.register(email, password)

    override fun currentUser() = firebaseAuthDataSource.currentUser()

    override fun logout() = firebaseAuthDataSource.logout()
}