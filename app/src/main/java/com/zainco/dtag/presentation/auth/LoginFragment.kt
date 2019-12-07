package com.zainco.dtag.presentation.auth

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.zainco.dtag.R
import com.zainco.dtag.databinding.LoginFragmentBinding
import com.zainco.dtag.extension.toast
import com.zainco.dtag.presentation.auth.validations.AuthFields
import com.zainco.dtag.presentation.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class LoginFragment : BindingFragment<LoginFragmentBinding>(), AuthListener {

    private val viewModelFactory: AuthViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: AuthViewModel

    override val layoutResId: Int
        get() = R.layout.login_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = viewModel
        viewModel.getLoginFields().observe(this,
            Observer<AuthFields> { loginModel ->
                viewModel.login(loginModel.email, loginModel.password)
            })
        binding.textViewSignup.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnSkip.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_notesListFragment)
        }
        viewModel.authListener = this
        checkIfUserLoggedIn()

    }

    private fun checkIfUserLoggedIn() {
        if (viewModel.isUserLoggedIn()) {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_notesListFragment)
        }
    }

    override fun onSuccess() {
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_notesListFragment)
    }

    override fun onFailure(message: String) {
        context?.toast(message)
    }
}
