package com.zainco.dtag.data.notes.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Completable
import io.reactivex.Single

interface NotesLocalDataSource {
    val errorLoading: LiveData<String>
    val loading: LiveData<Boolean>
    fun getNotes(): LiveData<List<Note>>
    fun insert(note: Note): Completable
    fun update(note: Note): Completable
    fun delete(note: Note): Completable
    fun deleteAllNotes(): Completable
}