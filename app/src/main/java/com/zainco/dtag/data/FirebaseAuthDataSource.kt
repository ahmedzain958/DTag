package com.zainco.dtag.data

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable

interface FirebaseAuthDataSource {
    val errorLoading: LiveData<String>
    val loading: LiveData<Boolean>
    fun login(email: String, password: String): Completable

    fun register(email: String, password: String): Completable

    fun currentUser(): FirebaseUser?

    fun logout()
}