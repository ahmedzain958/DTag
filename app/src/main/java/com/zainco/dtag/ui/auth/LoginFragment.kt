package com.zainco.dtag.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.zainco.dtag.R
import com.zainco.dtag.databinding.LoginFragmentBinding
import com.zainco.dtag.ui.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class LoginFragment : BindingFragment<LoginFragmentBinding>() {

    private val viewModelFactory: AuthViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: AuthViewModel

    override val layoutResId: Int
        get() = R.layout.login_fragment


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        binding.textViewSignup.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}
