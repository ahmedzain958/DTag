package com.zainco.dtag.data.notes.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.zainco.dtag.data.auth.FirebaseAuthDataSource
import com.zainco.dtag.data.notes.entities.Note
import com.zainco.dtag.data.notes.remote.RemoteConstants.NOTEBOOK_COLLECTION

class NotesRemoteDataSourceImpl(
    private val firebaseAuthDataSource: FirebaseAuthDataSource,
    private val firebaseFirestore: FirebaseFirestore
) :
    NotesRemoteDataSource {

    override fun currentUser(): FirebaseUser? {
        return firebaseAuthDataSource.currentUser()
    }

    override fun insertNote(note: Note) {
        firebaseFirestore.collection(NOTEBOOK_COLLECTION).document(note.title)
            .set(note).addOnSuccessListener {
                Log.d(
                    "zzzzzz",
                    note.title + "DocumentSnapshot successfully inserted!"
                )
            }.addOnFailureListener {
                Log.d(
                    "zzzzzz",
                    note.title + "Error inserted document "+it.message

                )
            }

    }

    override fun getNotes(): LiveData<List<Note>> {
        val noteList = MutableLiveData<List<Note>>()
        firebaseFirestore.collection(NOTEBOOK_COLLECTION)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    return@addSnapshotListener
                }
                if (querySnapshot == null) {
                    //no data
                } else {
                    if (querySnapshot.size() > 0) {
                        val notesList = mutableListOf<Note>()
                        querySnapshot.forEach { documentSnapshot ->
                            val note = documentSnapshot.toObject(Note::class.java)
                            notesList.add(note)
                        }
                        noteList.postValue(notesList)
                    }
                }
            }
        return noteList
    }

    override fun deleteNote(note: Note) {
        firebaseFirestore.collection(NOTEBOOK_COLLECTION).document(note.title)
            .delete()
            .addOnSuccessListener {
                Log.d(
                    "zzzzzz",
                    note.title + "DocumentSnapshot successfully deleted!"
                )
            }
            .addOnFailureListener { e ->
                Log.d(
                    "zzzzzz",
                    note.title + "Error deleting document "+e.message

                )
            }

    }

    override fun updateNote(note: Note) {
        firebaseFirestore.collection(NOTEBOOK_COLLECTION).document(note.title)
            .set(note, SetOptions.merge())
            .addOnSuccessListener {
                Log.d(
                    "zzzzzz",
                    note.title + "DocumentSnapshot successfully deleted!"
                )
            }
            .addOnFailureListener { e ->
                Log.d(
                    "zzzzzz",
                    note.title + "Error deleting document",
                    e
                )
            }

    }
}