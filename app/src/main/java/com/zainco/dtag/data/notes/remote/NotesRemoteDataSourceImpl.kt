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
import io.reactivex.Observable
import io.reactivex.Single

class NotesRemoteDataSourceImpl(
    private val firebaseAuthDataSource: FirebaseAuthDataSource,
    private val firebaseFirestore: FirebaseFirestore
) :
    NotesRemoteDataSource {
    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean>
        get() = _loading

    private val _errorLoading = MutableLiveData<String>()
    override val errorLoading: LiveData<String>
        get() = _errorLoading


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
                    note.title + "Error inserted document " + it.message

                )
            }

    }

    override fun getNotes(): Observable<List<Note>> {
        _loading.postValue(true)
        return Observable.create<List<Note>> { emitter ->
            val addSnapshotListener = firebaseFirestore.collection(NOTEBOOK_COLLECTION)
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        return@addSnapshotListener
                    }
                    if (querySnapshot == null) {
                        _errorLoading.postValue("Error Loading Data")
                    } else {
                        if (querySnapshot.size() > 0) {
                            val notesList = mutableListOf<Note>()
                            querySnapshot.forEach { documentSnapshot ->
                                Log.d(
                                    "zzzzzz",
                                    documentSnapshot.toString()
                                )
                                val note = documentSnapshot.toObject(Note::class.java)
                                notesList.add(note)
                                emitter.onNext(notesList)
                            }
                        } else {
                            _errorLoading.postValue("No Data Available")
                        }
                    }
                }
            _loading.postValue(false)
            emitter.setCancellable {
                addSnapshotListener.remove()
            }

        }
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
                    note.title + "Error deleting document " + e.message

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