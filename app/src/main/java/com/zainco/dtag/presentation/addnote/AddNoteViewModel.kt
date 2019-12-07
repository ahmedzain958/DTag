package com.zainco.dtag.presentation.addnote

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.extension.closeSoftKeyboard


class AddNoteViewModel(
    private val repository: NotesRepository
) : BaseViewModel() {
    var title: String? = null
    var description: String? = null

    fun onSaveClick(view: View) {
        val note = Note(title = title ?: "", description = description ?: "")
        view.closeSoftKeyboard()
        repository.insertNote(note)
    }

    fun editNote(noteId: Int) {
        val note = Note(title = title ?: "", description = description ?: "")
        note.id = noteId
        repository.updateNote(note)
    }
}
