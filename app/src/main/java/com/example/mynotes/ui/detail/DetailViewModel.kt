package com.example.mynotes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.data.NotesDatabase
import com.example.mynotes.data.NotesRepository
import com.example.mynotes.domain.GetByIdUseCase
import com.example.mynotes.domain.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getByIdUseCase: GetByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId: Int
) :
    ViewModel() {

    private val _state = MutableStateFlow(Note(0, "", ""))
    val state: StateFlow<Note> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            val note = getByIdUseCase(noteId)
            if (note != null) {
                _state.value = note
            }
        }
    }

    fun save(title: String, description: String) {
        viewModelScope.launch {
            val nota = _state.value.copy(title = title, description = description)
            saveNoteUseCase(nota)
        }


    }

}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val getByIdUseCase: GetByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(getByIdUseCase, saveNoteUseCase, noteId) as T
    }

}