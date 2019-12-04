package com.zainco.dtag.data

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthDataSource {


    override fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    override fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful)
                        emitter.onComplete()
                    else
                        emitter.onError(it.exception!!)
                }
            }
        }
    }

    override fun logout() = firebaseAuth.signOut()

    override fun currentUser() = firebaseAuth.currentUser
}