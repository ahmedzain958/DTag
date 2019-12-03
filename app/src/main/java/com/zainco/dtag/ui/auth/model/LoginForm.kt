package com.zainco.dtag.ui.auth.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.zainco.dtag.BR
import com.zainco.dtag.R

class LoginForm : BaseObservable() {
    val fields = LoginFields()
    private val errors = LoginErrorFields()
    val loginFields = MutableLiveData<LoginFields>()
    @get:Bindable
    val isValid: Boolean
        get() {
            var valid = isEmailValid(false)
            valid = isPasswordValid(false) && valid
            notifyPropertyChanged(BR.emailError)
            notifyPropertyChanged(BR.passwordError)
            return valid
        }

    fun isEmailValid(setMessage: Boolean): Boolean { // Minimum a@b.c
        val email = fields.email
        if (email != null && email.length > 5) {
            val indexOfAt = email.indexOf("@")
            val indexOfDot = email.lastIndexOf(".")
            return if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email.length - 1) {
                errors.email = null
                notifyPropertyChanged(BR.valid)
                true
            } else {
                if (setMessage) {
                    errors.email = R.string.error_format_invalid
                    notifyPropertyChanged(BR.valid)
                }
                false
            }
        }
        if (setMessage) {
            errors.email = R.string.error_too_short
            notifyPropertyChanged(BR.valid)
        }
        return false
    }

    fun isPasswordValid(setMessage: Boolean): Boolean {
        val password = fields.password
        return if (password != null && password.length > 5) {
            errors.password = null
            notifyPropertyChanged(BR.valid)
            true
        } else {
            if (setMessage) {
                errors.password = R.string.error_too_short
                notifyPropertyChanged(BR.valid)
            }
            false
        }
    }

    fun onClick() {
        if (isValid) {
            loginFields.value = fields
        }
    }

    @get:Bindable
    val emailError: Int?
        get() = errors.email

    @get:Bindable
    val passwordError: Int?
        get() = errors.password
}