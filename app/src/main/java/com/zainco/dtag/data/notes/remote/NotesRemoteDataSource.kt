package com.zainco.dtag.data.notes.remote

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Observable
import io.reactivex.Single

interface NotesRemoteDataSource {
    val errorLoading: LiveData<String>
    val loading: LiveData<Boolean>
    fun currentUser(): FirebaseUser?
    fun insertNote(note: Note)
    fun getNotes(): Observable<List<Note>>
    fun deleteNote(note: Note)
    fun updateNote(note: Note)
}