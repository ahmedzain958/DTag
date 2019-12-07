package com.zainco.dtag.data.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Observable
import io.reactivex.Single

interface NotesRepository {
    val errorLiveData: MutableLiveData<String>
    val loading: LiveData<Boolean>
    fun getAllNotes(): Observable<List<Note>>
    fun insertNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNote(note: Note)
    fun deleteAllNotes()
}