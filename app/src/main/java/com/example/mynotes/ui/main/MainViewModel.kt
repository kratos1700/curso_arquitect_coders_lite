package com.example.mynotes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.data.NotesDatabase
import com.example.mynotes.data.NotesRepository
import com.example.mynotes.domain.DeleteNotesUseCase
import com.example.mynotes.domain.GetCurrentNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// pasamos por parametro el NotesDatabase
@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase
) : ViewModel() {

    //necesitamos un estado que represente el estado del UI,
    // si se cambia lo representa
    val state = getCurrentNotesUseCase()

    fun onNoteDelete(nota: Note) {
        viewModelScope.launch {
            //eliminamos nota
            deleteNotesUseCase(nota)
        }
    }

}

/*
//ANULADO POR DAGER HILT
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCurrentNotesUseCase, deleteNotesUseCase) as T
    }

}*/