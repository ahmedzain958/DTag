package com.zainco.dtag.presentation.notelist.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import androidx.paging.PageKeyedDataSource
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note


class ItemDataSourceFactory(private val notesRepository: NotesRepository) : Factory<Int, Note>() {
    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Note>>()
    override fun create(): DataSource<Int, Note> {
        //getting our data source object
        val notesDataSource = NotesDataSource(notesRepository)

        //posting the datasource to get the values
        itemLiveDataSource.postValue(notesDataSource)

        //returning the datasource
        return notesDataSource
    }

    //getter for itemlivedatasource
    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Note>> {
        return itemLiveDataSource
    }
}