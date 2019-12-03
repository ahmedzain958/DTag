package com.zainco.dtag.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AuthRepositoryImpl(private val firebaseAuthDataSource: FirebaseAuthDataSource) :
    AuthRepository {
    private val _errorLiveData = MutableLiveData<String>()
    override val errorLiveData: LiveData<String>
        get() = _errorLiveData

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean>
        get() = _loading

    init {
        firebaseAuthDataSource.errorLoading.observeForever {
            _errorLiveData.postValue(it)
        }
        firebaseAuthDataSource.loading.observeForever {
            _loading.postValue(it)
        }
    }

    override fun login(email: String, password: String) =
        firebaseAuthDataSource.login(email, password)

    override fun register(email: String, password: String) =
        firebaseAuthDataSource.register(email, password)

    override fun currentUser() = firebaseAuthDataSource.currentUser()

    override fun logout() = firebaseAuthDataSource.logout()
}