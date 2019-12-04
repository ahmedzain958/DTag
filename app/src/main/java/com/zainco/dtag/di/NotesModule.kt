package com.zainco.dtag.di

import com.zainco.dtag.data.auth.FirebaseAuthDataSource
import com.zainco.dtag.data.auth.FirebaseAuthDataSourceImpl
import com.zainco.dtag.data.notes.NotesRepository
import com.zainco.dtag.data.notes.NotesRepositoryImpl
import com.zainco.dtag.ui.notes.NotesViewModelFactory
import org.koin.dsl.module.module


@JvmField
val notesModule = module {
    factory<NotesRepository> { NotesRepositoryImpl() }
    factory { NotesViewModelFactory(get()) }
}