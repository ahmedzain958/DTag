package com.zainco.dtag.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthDataSource {
    private val _loading = MutableLiveData<Boolean>()
    private val _errorLoading = MutableLiveData<String>()

    override val loading: LiveData<Boolean>
        get() = _loading
    override val errorLoading: LiveData<String>
        get() = _errorLoading

    override fun login(email: String, password: String) = Completable.create { emitter ->
        _loading.postValue(true)
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            _loading.postValue(false)
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    override fun register(email: String, password: String) = Completable.create { emitter ->
        _loading.postValue(true)
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            _loading.postValue(false)
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    override fun logout() = firebaseAuth.signOut()

    override fun currentUser() = firebaseAuth.currentUser
}