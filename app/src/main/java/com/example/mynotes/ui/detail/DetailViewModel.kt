package com.example.mynotes.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.data.NotesDatabase
import com.example.mynotes.data.NotesRepository
import com.example.mynotes.domain.GetByIdUseCase
import com.example.mynotes.domain.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getByIdUseCase: GetByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId:Int
    // private val savedStateHandle: SavedStateHandle,

) :
    ViewModel() {

    private val _state = MutableStateFlow(Note(0, "", ""))
    val state: StateFlow<Note> = _state.asStateFlow()

    //private val noteId = requireNotNull(savedStateHandle.get<Int>(DetailActivity.EXTRA_NOTE_ID))
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

/*
ANULADO POR DAGER HILT

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

}*/
