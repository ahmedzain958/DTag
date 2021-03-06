package com.zainco.dtag.presentation.addnote

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.databinding.FragmentAddNoteBinding
import com.zainco.dtag.extension.toast
import com.zainco.dtag.presentation.auth.AuthListener
import com.zainco.dtag.presentation.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class AddNoteFragment : BindingFragment<FragmentAddNoteBinding>(),AuthListener {


    private val addNoteViewModelFactory: AddNoteViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: AddNoteViewModel
    override val layoutResId: Int
        get() = com.zainco.dtag.R.layout.fragment_add_note

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, addNoteViewModelFactory).get(AddNoteViewModel::class.java)
        binding.viewmodel = viewModel
        arguments?.getSerializable("note")?.let {
            val note = arguments?.getSerializable("note") as Note
            binding.btSave.visibility = View.INVISIBLE
            binding.btEdit.visibility = View.VISIBLE
            viewModel.title = note.title
            viewModel.description = note.description
            binding.btEdit.setOnClickListener {
                viewModel.editNote(note.id!!)
            }
        }
        binding.btCancel.setOnClickListener {
            view?.findNavController()?.navigateUp()
        }

    }
    override fun onSuccess() {
        view?.findNavController()?.navigateUp()
    }

    override fun onFailure(message: String) {
        context?.toast(message)
    }
}
