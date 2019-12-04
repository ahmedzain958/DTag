package com.zainco.dtag.ui.notes

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.zainco.dtag.R
import com.zainco.dtag.databinding.NotesListFragmentBinding
import com.zainco.dtag.ui.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NotesListFragment : BindingFragment<NotesListFragmentBinding>() {

    private val notesViewModelFactory: NotesViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: NotesViewModel
    override val layoutResId: Int
        get() = R.layout.notes_list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, notesViewModelFactory).get(NotesViewModel::class.java)
        binding.viewmodel = viewModel

    }

}
