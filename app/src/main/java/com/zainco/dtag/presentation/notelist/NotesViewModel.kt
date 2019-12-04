package com.zainco.dtag.presentation.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class NotesViewModel(
    private val repository: NotesRepository
) : BaseViewModel() {
    val loading = repository.loading
    val errorMsg = repository.errorLiveData
    val error = MutableLiveData<Boolean>()
    private val noteList = MutableLiveData<List<Note>>()
    val observableNoteList: LiveData<List<Note>>
        get() = noteList

    init {
        error.postValue(false)
        errorMsg.observeForever {
            error.postValue(true)
        }
    }



    fun update(note: Note) {
        addToDisposable(
            Observable.fromCallable { repository.updateNote(note) }
                .subscribeOn(Schedulers.computation())
                .subscribe()
        )
    }

    fun delete(note: Note) {
        addToDisposable(
            Observable.fromCallable { repository.deleteNote(note) }
                .subscribeOn(Schedulers.computation())
                .subscribe()
        )
    }

    fun deleteAllNotes() {
        addToDisposable(
            Observable.fromCallable { repository.deleteAllNotes() }
                .subscribeOn(Schedulers.computation())
                .subscribe()
        )
    }

    fun loadNotes() {
        repository.getAllNotes()
        noteList.value =repository.noteLiveData.value
    }

}
