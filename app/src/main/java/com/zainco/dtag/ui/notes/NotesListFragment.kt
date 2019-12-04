package com.zainco.dtag.ui.notes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainco.dtag.R
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.databinding.NotesListFragmentBinding
import com.zainco.dtag.ui.base.BindingFragment
import kotlinx.android.synthetic.main.notes_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NotesListFragment : BindingFragment<NotesListFragmentBinding>() {

    private val clickListener: ClickListener = this::onNoteClicked
    private val notesViewModelFactory: NotesViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: NotesViewModel
    private val recyclerViewAdapter = NotesAdapter(clickListener)
    override val layoutResId: Int
        get() = R.layout.notes_list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel =
            ViewModelProviders.of(this, notesViewModelFactory).get(NotesViewModel::class.java)
        binding.viewmodel = viewModel
        binding.fab.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_notesListFragment_to_addNoteFragment)
        }
    }

    private fun render(noteList: List<Note>) {
        recyclerViewAdapter.updateNotes(noteList)
        if (noteList.isEmpty()) {
            binding.notesRecyclerView.visibility = View.GONE
        } else {
            binding.notesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadNotes()
        viewModel.observableNoteList.observe(this, Observer { notes ->
            notes?.let { render(notes) }
        })
    }

    private fun onNoteClicked(note: Note) {

    }

    private fun setupRecyclerView() {
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.notesRecyclerView.adapter = recyclerViewAdapter
        binding.notesRecyclerView.setHasFixedSize(true)
    }
}
