package com.zainco.dtag.presentation.auth

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.zainco.dtag.R
import com.zainco.dtag.databinding.SignUpFragmentBinding
import com.zainco.dtag.extension.toast
import com.zainco.dtag.presentation.auth.validations.AuthFields
import com.zainco.dtag.presentation.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SignUpFragment : BindingFragment<SignUpFragmentBinding>(), AuthListener {

    private val viewModelFactory: AuthViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: AuthViewModel
    override val layoutResId: Int
        get() = R.layout.sign_up_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.getLoginFields().observe(this,
            Observer<AuthFields> { loginModel ->
                Toast.makeText(
                    context,
                    "Email " + loginModel.email + ", Password " + loginModel.password,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.signup(loginModel.email, loginModel.password)
            })
        binding.textViewLogin.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        viewModel.authListener = this
    }


    override fun onSuccess() {
        view?.findNavController()?.navigate(
            R.id.action_signUpFragment_to_notesListFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(
                    R.id.signUpFragment,
                    true
                ).build()
        )
    }

    override fun onFailure(message: String) {
        context?.toast(message)
    }

}
