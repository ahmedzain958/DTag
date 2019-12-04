package com.zainco.dtag.data.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leopold.mvvm.extension.with
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.data.notes.local.NotesLocalDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    private var charactersDisposable: Disposable? = null
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
        charactersDisposable?.let { if (!it.isDisposed) it.dispose() }
        notesLocalDataSource.errorLoading.observeForever {
            _errorLiveData.postValue(it)
        }

        notesLocalDataSource.loading.observeForever {
            _loading.postValue(it)
        }
    }

    override fun getAllNotes() {
        charactersDisposable = notesLocalDataSource.getNotes().with()
            .subscribe({
                _notesListLiveData.value = it
            }, { throwable ->
                when (throwable) {

                }
            })
    }


    override fun insertNote(note: Note) {
        charactersDisposable =
            notesLocalDataSource.insert(note).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        //inserted
                    },
                    { throwable ->
                        when (throwable) {

                        }
                    }
                )
    }

    override fun updateNote(note: Note) {
        charactersDisposable = notesLocalDataSource.update(note).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }

    override fun deleteNote(note: Note) {
        charactersDisposable = notesLocalDataSource.delete(note).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }

    override fun deleteAllNotes() {
//        noteDao.deleteAllNotes()
    }
}