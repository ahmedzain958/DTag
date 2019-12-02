package com.zainco.dtag.ui.auth

import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.repository.AuthRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val repository: AuthRepository

) : BaseViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun login() {
        //validating email and password
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        //authentication started
        authListener?.onStarted()

        //calling login from repository to perform the actual authentication
        addToDisposable(repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //sending a success callback
                authListener?.onSuccess()
            }, {
                //sending a failure callback
                authListener?.onFailure(it.message!!)
            })
        )
    }

    fun signup() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        addToDisposable(
            repository.register(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()
                }, {
                    authListener?.onFailure(it.message!!)
                })
        )
    }

}
