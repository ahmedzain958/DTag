package com.zainco.dtag.presentation.notelist.paging

import androidx.paging.PageKeyedDataSource
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note


class NotesDataSource(private val notesRepository: NotesRepository) :
    PageKeyedDataSource<Int, Note>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Note>
    ) {
        val notes: List<Note>? = notesRepository.noteLiveData.value
        callback.onResult(notes ?: listOf(), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Note>) {
        val notes = notesRepository.noteLiveData.value
        val key = (if (params.key == 1) params.key + 1 else null)?.toInt()
        callback.onResult(notes ?: listOf(), key);

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Note>) {
        val notes = notesRepository.noteLiveData.value
        val key = (if (params.key > 1) params.key - 1 else null)?.toInt()
        callback.onResult(notes ?: listOf(), key);
    }
}