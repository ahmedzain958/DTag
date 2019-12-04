package com.zainco.dtag.data.notes.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.data.notes.local.LocalConstants.DATABASE_VERSION


@Database(entities = [Note::class], version = DATABASE_VERSION)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}