package com.zainco.dtag.presentation.notelist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zainco.dtag.R
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.databinding.NotesListFragmentBinding
import com.zainco.dtag.extension.toast
import com.zainco.dtag.presentation.base.BindingFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class NotesListFragment : BindingFragment<NotesListFragmentBinding>() {

    private val clickListener: ClickListener = this::onNoteClicked
    private val notesViewModelFactory: NotesViewModelFactory by inject { parametersOf(this) }
    private lateinit var viewModel: NotesViewModel
    private val recyclerViewAdapter = NotesAdapter(clickListener)
    override val layoutResId: Int
        get() = R.layout.notes_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel =
            ViewModelProviders.of(this, notesViewModelFactory).get(NotesViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.observableNoteList.observe(this, Observer { notes ->
            notes?.let { render(notes) }
        })
        binding.fab.setOnClickListener {
            view.findNavController().navigate(R.id.action_notesListFragment_to_addNoteFragment)
        }
        onSwipe()
    }

    fun onSwipe() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.delete(recyclerViewAdapter.getNoteAt(viewHolder.adapterPosition)!!)
                context?.toast("Note deleted")
            }
        }).attachToRecyclerView(binding.notesRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadNotes()
    }

    private fun render(noteList: List<Note>) {
        recyclerViewAdapter.updateNotes(noteList)
        if (noteList.isEmpty()) {
            binding.notesRecyclerView.visibility = View.GONE
            binding.notesNotFound.visibility = View.VISIBLE
        } else {
            binding.notesRecyclerView.visibility = View.VISIBLE
            binding.notesNotFound.visibility = View.GONE
        }
    }

    private fun onNoteClicked(note: Note) {
        view?.findNavController()
            ?.navigate(
                NotesListFragmentDirections.actionNotesListFragmentToAddNoteFragment(note),
                NavOptions.Builder()
                    .setPopUpTo(
                        R.id.notesListFragment,
                        true
                    ).build()
            )
    }

    private fun setupRecyclerView() {
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.notesRecyclerView.adapter = recyclerViewAdapter
        binding.notesRecyclerView.setHasFixedSize(true)
    }
}
