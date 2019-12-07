package com.zainco.dtag.data.notes.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun insert(note: Note): Completable

    @Update
    fun update(note: Note): Completable

    @Delete
    fun delete(note: Note): Completable

    @Query("DELETE FROM note_table")
    fun deleteAllNotes(): Completable

    @Query("SELECT * FROM note_table ORDER BY id")
    fun getAllNotes(): Observable<List<Note>>
}