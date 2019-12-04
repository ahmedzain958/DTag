package com.zainco.dtag.data.notes.local

import androidx.lifecycle.LiveData
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Single

interface NotesLocalDataSource {
    val errorLoading: LiveData<String>
    val loading: LiveData<Boolean>
    fun getNotes(): Single<List<Note>>
}