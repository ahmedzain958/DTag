package com.zainco.dtag.data.notes.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Completable
import io.reactivex.Single

class NotesLocalDataSourceImpl(
    private val noteDao: NoteDao
) : NotesLocalDataSource {

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean>
        get() = _loading

    private val _errorLoading = MutableLiveData<String>()
    override val errorLoading: LiveData<String>
        get() = _errorLoading

    override fun getNotes(): Single<List<Note>> {
        return noteDao.getAllNotes()
    }

    override fun insert(note: Note): Completable {
        return noteDao.insert(note)
    }

    override fun update(note: Note): Completable {
        return noteDao.update(note)
    }

    override fun delete(note: Note): Completable {
        return noteDao.delete(note)
    }

    override fun deleteAllNotes(): Completable {
        return noteDao.deleteAllNotes()
    }

}