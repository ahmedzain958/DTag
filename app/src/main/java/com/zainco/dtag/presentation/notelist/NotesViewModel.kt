package com.zainco.dtag.presentation.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NotesViewModel(
    private val repository: NotesRepository
) : BaseViewModel() {
    val loading = repository.loading
    var errorMsg: MutableLiveData<String> = repository.errorLiveData
    val error = MutableLiveData<Boolean>()
    private val _observableNoteList = MutableLiveData<List<Note>>()
    val observableNoteList: LiveData<List<Note>>
        get() = _observableNoteList


    /*var itemPagedList: LiveData<PagedList<Note>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Note>>? = null*/


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
    fun retry() {
        error.postValue(false)
        loadNotes()
    }
    fun loadNotes() {
        addToDisposable(repository.getAllNotes().subscribe { notesList: List<Note> ->
            if (notesList.isEmpty())
                errorMsg.postValue("No Data Available")
            else {
                _observableNoteList.postValue(notesList)
            }
        })
        /* val itemDataSourceFactory = ItemDataSourceFactory(repository)

         //getting the live data source from data source factory
         liveDataSource = itemDataSourceFactory.getItemLiveDataSource()

         //Getting PagedList config
         val pagedListConfig = PagedList.Config.Builder()
             .setEnablePlaceholders(false)
             .setPageSize(PAGE_SIZE).build()

         itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
             .build();

        noteList.postValue(_observableNoteList?.value)*/
    }

}
