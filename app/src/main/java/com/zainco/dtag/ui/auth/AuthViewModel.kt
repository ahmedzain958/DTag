package com.zainco.dtag.ui.auth

import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.AuthRepository
import com.zainco.dtag.ui.auth.model.LoginFields
import com.zainco.dtag.ui.auth.model.LoginForm

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel() {
    var authListener: AuthListener? = null
    val loading = repository.loading
    val errorMsg = repository.errorLiveData
      var login: LoginForm = LoginForm()

    private var onFocusEmail: OnFocusChangeListener
    private var onFocusPassword: OnFocusChangeListener

    init {
        onFocusEmail = OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.isNotEmpty() && !focused) {
                login.isEmailValid(true)
            }
        }
        onFocusPassword = OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.isNotEmpty() && !focused) {
                login.isPasswordValid(true)
            }
        }
    }

    fun getEmailOnFocusChangeListener(): OnFocusChangeListener {
        return onFocusEmail
    }

    fun getPasswordOnFocusChangeListener(): OnFocusChangeListener {
        return onFocusPassword
    }


    fun login() {
        //validating email and password
      /*  if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        //authentication started
        authListener?.onStarted()

        //calling login from repository to perform the actual authentication
        addToDisposable(
            repository.login(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //sending a success callback
                    authListener?.onSuccess()
                }, {
                    //sending a failure callback
                    authListener?.onFailure(it.message!!)
                })
        )*/
    }

    fun signup() {
      /*  if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
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
        )*/
    }
    fun getLoginFields(): MutableLiveData<LoginFields> {
        return login.loginFields
    }
    fun onButtonClick() {
        login.onClick()
    }

    companion object {
        @BindingAdapter("error")
        @JvmStatic
        fun setError(editText: EditText, strOrResId: Any?) {
            if (strOrResId is Int) {
                editText.error = editText.context.getString((strOrResId as Int?)!!)
            } else {
                editText.error = strOrResId as String?
            }
        }

        @BindingAdapter("onFocus")
        @JvmStatic
        fun bindFocusChange(
            editText: EditText,
            onFocusChangeListener: OnFocusChangeListener?
        ) {
            if (editText.onFocusChangeListener == null) {
                editText.onFocusChangeListener = onFocusChangeListener
            }
        }
    }

}
