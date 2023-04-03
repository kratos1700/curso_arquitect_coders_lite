package com.example.mynotes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.data.NotesDatabase
import com.example.mynotes.data.NotesRepository
import kotlinx.coroutines.launch

// pasamos por parametro el NotesDatabase
class MainViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    //necesitamos un estado que represente el estado del UI,
    // si se cambia lo representa
    val state = notesRepository.currentNotes

    fun onNoteDelete(nota: Note) {
        viewModelScope.launch {
            //eliminamos nota
            notesRepository.delete(nota)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val notesRepository: NotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(notesRepository) as T
    }

}