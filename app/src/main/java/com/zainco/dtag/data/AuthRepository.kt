package com.zainco.dtag.data

import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable

interface AuthRepository {

    fun login(email: String, password: String): Completable

    fun register(email: String, password: String): Completable

    fun currentUser(): FirebaseUser?

    fun logout()
}