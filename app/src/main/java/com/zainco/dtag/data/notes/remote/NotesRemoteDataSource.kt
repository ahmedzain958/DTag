package com.zainco.dtag.data.notes.remote

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import com.zainco.dtag.data.notes.entities.Note

interface NotesRemoteDataSource {
    fun currentUser(): FirebaseUser?
    fun insertNote(note: Note)
    fun getNotes(pageSize:Int): LiveData<List<Note>>
    fun deleteNote(note: Note)
    fun updateNote(note: Note)
}