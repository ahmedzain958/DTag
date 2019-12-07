package com.zainco.dtag.presentation.notelist

import android.content.ClipData.Item
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.presentation.notelist.paging.ItemDataSourceFactory
import com.zainco.dtag.presentation.notelist.paging.NotesDataSource
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


    var itemPagedList: LiveData<PagedList<Note>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Note>>? = null


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


        val itemDataSourceFactory = ItemDataSourceFactory(repository)

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
            .build();

        noteList.postValue(itemPagedList?.value)
    }

}
