package com.zainco.dtag.presentation.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.dtag.R
import com.zainco.dtag.data.notes.entities.Note


typealias ClickListener = (Note) -> Unit

class NotesAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var noteList = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemContainer = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false) as ViewGroup
        val viewHolder = NotesViewHolder(itemContainer)
        itemContainer.setOnClickListener {
            clickListener(noteList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title
        holder.desc.text = note.description
    }

    override fun getItemCount() = noteList.size
    fun getNoteAt(position: Int): Note? {
        return noteList[position]
    }

    fun updateNotes(noteList: List<Note>) {
        val diffResult = DiffUtil.calculateDiff(NoteDiffCallback(this.noteList, noteList))
        this.noteList = noteList
        diffResult.dispatchUpdatesTo(this)
    }

    class NotesViewHolder(itemViewGroup: ViewGroup) : RecyclerView.ViewHolder(itemViewGroup) {
        val title: TextView = itemViewGroup.findViewById(R.id.text_view_title)
        val desc: TextView = itemViewGroup.findViewById(R.id.text_view_description)
    }

}