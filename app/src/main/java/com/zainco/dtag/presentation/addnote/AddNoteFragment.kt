package com.zainco.dtag.presentation.addnote

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.zainco.dtag.R
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.databinding.FragmentAddNoteBinding
import com.zainco.dtag.presentation.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AddNoteFragment : BindingFragment<FragmentAddNoteBinding>() {

    private val addNoteViewModelFactory: AddNoteViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: AddNoteViewModel
    override val layoutResId: Int
        get() = R.layout.fragment_add_note

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, addNoteViewModelFactory).get(AddNoteViewModel::class.java)
        binding.viewmodel = viewModel
        arguments?.getSerializable("note")?.let {
            val note = arguments?.getSerializable("note") as Note
            binding.btSave.visibility = View.INVISIBLE
            binding.btEdit.visibility = View.VISIBLE
            binding.edTitle.setText(note.title)
            binding.edDesc.setText(note.description)
            binding.btEdit.setOnClickListener {
                viewModel.editNote(note.id!!)
            }
        }
    }

}
