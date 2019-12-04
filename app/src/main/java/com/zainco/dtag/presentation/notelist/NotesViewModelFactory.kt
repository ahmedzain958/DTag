package com.zainco.dtag.presentation.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zainco.dtag.data.notes.NotesRepository

class NotesViewModelFactory(
    private val notesRepository: NotesRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}