package com.zainco.dtag.presentation.notelist

import androidx.recyclerview.widget.DiffUtil
import com.zainco.dtag.data.notes.entities.Note

class NoteDiffCallback(
    private val old: List<Note>,
    private val new: List<Note>
) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex].id == new[newIndex].id
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex].id == new[newIndex].id && old[oldIndex].title == new[newIndex].title
                && old[oldIndex].description == new[newIndex].description
    }
}