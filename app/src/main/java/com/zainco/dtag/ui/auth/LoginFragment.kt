package com.zainco.dtag.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.zainco.dtag.R
import com.zainco.dtag.databinding.LoginFragmentBinding
import com.zainco.dtag.ui.base.BindingFragment


class LoginFragment : BindingFragment<LoginFragmentBinding>() {

    private lateinit var viewModel: AuthViewModel

    override val layoutResId: Int
        get() = R.layout.login_fragment


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.textViewRegister.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}
