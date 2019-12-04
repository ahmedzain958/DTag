package com.zainco.dtag.ui.notes

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.leopold.mvvm.core.BaseViewModel
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class NotesViewModel(
    private val repository: NotesRepository
) : BaseViewModel() {
    var title: String? = null
    var description: String? = null
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

   /* companion object {
        @BindingAdapter(value = ["list", "viewModel"])
        @JvmStatic
        fun setList(
            view: RecyclerView,
            listModel: LiveData<List<Note>>,
            viewModel: NotesViewModel?
        ) {
            if (listModel == null) return
            if (view.adapter != null) {
                if (view.adapter is NotesAdapter) {
                    val adapter = view.adapter as NotesAdapter
                    adapter.setNotes(listModel.value)
                    adapter.notifyDataSetChanged()
                }
            } else {
                val adapter = NotesAdapter(listModel.list.toMutableList(), viewModel!!)
                view.adapter = adapter
            }
        }
    }*/

    fun onSaveClick() {
        val note = Note(title!!, description!!, 1)
        insert(note)
    }

    fun insert(note: Note) {
        repository.insertNote(note)
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
