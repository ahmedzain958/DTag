package com.zainco.dtag.data.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.data.notes.local.NotesLocalDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    private val _errorLiveData = MutableLiveData<String>()
    override val errorLiveData: LiveData<String>
        get() = _errorLiveData
    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean>
        get() = _loading
    private val _notesListLiveData = MutableLiveData<List<Note>>()
    override val noteLiveData: LiveData<List<Note>>
        get() = _notesListLiveData

    init {
        notesLocalDataSource.errorLoading.observeForever {
            _errorLiveData.postValue(it)
        }

        notesLocalDataSource.loading.observeForever {
            _loading.postValue(it)
        }

        /*notesLocalDataSource.notesData.observeForever {

            if (it.list.isEmpty()) {
                _errorLiveData.postValue("No Data Available")
            } else
                _notesListLiveData.postValue(it)
        }*/
    }

    override fun getAllNotes() {
        notesLocalDataSource.getNotes().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _notesListLiveData.value = it
            }, { throwable ->

            })
    }


    override fun insertNote(note: Note) {
        /*   charactersDisposable?.let { if (!it.isDisposed) it.dispose() }
           charactersDisposable =
               noteDao.insert(note).subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(
                       {

                       },
                       { throwable ->
                           when (throwable) {

                           }
                       }
                   )*/
    }

    override fun updateNote(note: Note) {
//        noteDao.update(note)
    }

    override fun deleteNote(note: Note) {
//        noteDao.delete(note)
    }

    override fun deleteAllNotes() {
//        noteDao.deleteAllNotes()
    }


    private fun handleError(e: Throwable) {
    }

}