package com.example.mynotes.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.NotesDatabase
import kotlinx.coroutines.launch

// pasamos por parametro el NotesDatabase
class MainViewModel(private val db: NotesDatabase) : ViewModel() {

    //necesitamos un estado que represente el estado del UI,
    // si se cambia lo representa
    val state = db.notesDao().getAll()

    fun onNoteDelete(nota: Note) {
        viewModelScope.launch {
            //eliminamos nota
            db.notesDao().delete(nota)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val db: NotesDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }

}