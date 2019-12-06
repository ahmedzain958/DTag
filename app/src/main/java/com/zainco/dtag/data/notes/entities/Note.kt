package com.zainco.dtag.data.notes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
data class Note(var title: String="", var description: String="") : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
