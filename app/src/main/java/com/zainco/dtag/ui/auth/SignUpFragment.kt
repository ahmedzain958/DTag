package com.zainco.dtag.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.zainco.dtag.R
import com.zainco.dtag.databinding.SignUpFragmentBinding
import com.zainco.dtag.ui.base.BindingFragment

class SignUpFragment : BindingFragment<SignUpFragmentBinding>() {

    private lateinit var viewModel: AuthViewModel
    override val layoutResId: Int
        get() = R.layout.sign_up_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
