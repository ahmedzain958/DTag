package com.zainco.dtag.di

import androidx.room.Room
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.NotesRepositoryImpl
import com.zainco.dtag.data.notes.local.LocalConstants.NOTE_DATABASE
import com.zainco.dtag.data.notes.local.NoteDatabase
import com.zainco.dtag.data.notes.local.NotesLocalDataSource
import com.zainco.dtag.data.notes.local.NotesLocalDataSourceImpl
import com.zainco.dtag.data.notes.remote.NotesRemoteDataSource
import com.zainco.dtag.data.notes.remote.NotesRemoteDataSourceImpl
import com.zainco.dtag.presentation.addnote.AddNoteViewModelFactory
import com.zainco.dtag.presentation.notelist.NotesViewModelFactory
import org.koin.dsl.module.module


@JvmField
val notesModule = module {
    single {
        Room.databaseBuilder(get(), NoteDatabase::class.java, NOTE_DATABASE).build()
    }
    single {
        get<NoteDatabase>().noteDao()
    }
      single {
        get<NoteDatabase>().noteDao()
    }
    factory<NotesLocalDataSource> { NotesLocalDataSourceImpl(get()) }
    factory<NotesRemoteDataSource> { NotesRemoteDataSourceImpl(get()) }
    factory<NotesRepository> { NotesRepositoryImpl(get(),get()) }
    factory { NotesViewModelFactory(get()) }
    factory { AddNoteViewModelFactory(get()) }

}