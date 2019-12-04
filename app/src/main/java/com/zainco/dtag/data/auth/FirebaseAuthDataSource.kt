package com.zainco.dtag.data.auth

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable

interface FirebaseAuthDataSource {
    fun login(email: String, password: String): Completable

    fun register(email: String, password: String): Completable

    fun currentUser(): FirebaseUser?

    fun logout()
}