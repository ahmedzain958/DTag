package com.zainco.dtag.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.zainco.dtag.R
import com.zainco.dtag.databinding.ActivityMainBinding
import com.zainco.dtag.presentation.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>() {
    private lateinit var navController: NavController

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host_fragment)
    }
}
