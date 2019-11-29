package com.zainco.dtag.data.notes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
data class Note( @PrimaryKey(autoGenerate = true)
                 var id:Int = 0,var title: String="", var description: String="") : Serializable {

}
