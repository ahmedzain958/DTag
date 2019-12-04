package com.zainco.dtag.data.notes.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.zainco.dtag.data.notes.entities.Note
import io.reactivex.Single


@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): Single<List<Note>>
}