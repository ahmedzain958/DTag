package com.zainco.dtag.data.notes.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Single

class NotesLocalDataSourceImpl(
    private val noteDao: NoteDao
) : NotesLocalDataSource {

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean>
        get() = _loading

    override fun getNotes(): Single<List<Note>> {
      return  noteDao.getAllNotes()
    }

    private val _errorLoading = MutableLiveData<String>()
    override val errorLoading: LiveData<String>
        get() = _errorLoading


}