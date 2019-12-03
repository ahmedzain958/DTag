package com.zainco.dtag.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zainco.dtag.R
import com.zainco.dtag.databinding.SignUpFragmentBinding
import com.zainco.dtag.ui.auth.model.LoginFields
import com.zainco.dtag.ui.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SignUpFragment : BindingFragment<SignUpFragmentBinding>() {

    private val viewModelFactory: AuthViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: AuthViewModel
    override val layoutResId: Int
        get() = R.layout.sign_up_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.getLoginFields().observe(this,
            Observer<LoginFields> { loginModel ->
                Toast.makeText(
                    context,
                    "Email " + loginModel.email + ", Password " + loginModel.password,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

}
