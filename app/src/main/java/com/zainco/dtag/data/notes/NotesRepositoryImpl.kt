package com.zainco.dtag.data.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.data.notes.local.NotesLocalDataSource
import com.zainco.dtag.data.notes.remote.NotesRemoteDataSource
import com.zainco.dtag.presentation.auth.AuthListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource,
    private val notesRemoteDataSource: NotesRemoteDataSource
) : NotesRepository {
    var authListener: AuthListener? = null
    private var charactersDisposable: Disposable? = null
    override val errorLiveData = MutableLiveData<String>()
    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean>
        get() = _loading

    init {
        charactersDisposable?.let { if (!it.isDisposed) it.dispose() }
        notesLocalDataSource.errorLoading.observeForever {
            errorLiveData.postValue(it)
        }

        notesLocalDataSource.loading.observeForever {
            _loading.postValue(it)
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return notesRemoteDataSource.currentUser() != null
    }

    override fun getAllNotes(): Observable<List<Note>> {
        return if (isUserLoggedIn()) {
            notesRemoteDataSource.getNotes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        } else {
            notesLocalDataSource.getNotes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


    override fun insertNote(note: Note) {
        if (isUserLoggedIn()) {
            notesRemoteDataSource.insertNote(note)
        } else {
            charactersDisposable =
                notesLocalDataSource.insert(note).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            authListener?.onSuccess()
                        },
                        { throwable ->
                            authListener?.onFailure(throwable.message.toString())
                        }
                    )
        }
    }

    override fun updateNote(note: Note) {
        if (isUserLoggedIn()) {
            notesRemoteDataSource.updateNote(note)
        } else {
            charactersDisposable = notesLocalDataSource.update(note).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()
                },
                    { throwable ->
                        authListener?.onFailure(throwable.message.toString())
                    })
        }
    }

    override fun deleteNote(note: Note) {
        if (isUserLoggedIn()) {
            notesRemoteDataSource.deleteNote(note)
        } else {
            charactersDisposable = notesLocalDataSource.delete(note).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    authListener?.onSuccess()
                },
                    { throwable ->
                        authListener?.onFailure(throwable.message.toString())
                    })
        }
    }

    override fun deleteAllNotes() {
//        noteDao.deleteAllNotes()
    }
}