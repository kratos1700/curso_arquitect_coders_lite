package com.example.mynotes.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.NotesDatabase
import com.example.mynotes.main.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val db: NotesDatabase, private val noteId: Int) : ViewModel() {

    private val _state = MutableStateFlow(Note(0, "", ""))
    val state: StateFlow<Note> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            val note = db.notesDao().getById(noteId)
            if (note != null) {
                _state.value = note
            }
        }
    }

    fun save(title: String, description: String) {
        viewModelScope.launch {
            val nota = _state.value.copy(title = title, description = description)
            db.notesDao().insert(nota)
        }


    }

}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val db: NotesDatabase, private val noteId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(db, noteId) as T
    }

}