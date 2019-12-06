package com.zainco.dtag.data.notes.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
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

            }.addOnFailureListener {

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
}