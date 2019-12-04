package com.zainco.dtag.data.notes

import androidx.lifecycle.LiveData
import com.zainco.dtag.data.notes.entities.Note

interface NotesRepository {
    val errorLiveData: LiveData<String>
    val loading: LiveData<Boolean>
    val noteLiveData: LiveData<List<Note>>
    fun getAllNotes()
    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)

    fun deleteAllNotes()
}