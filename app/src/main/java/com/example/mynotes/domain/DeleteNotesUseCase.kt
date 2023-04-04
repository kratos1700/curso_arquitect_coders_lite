package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRepository

class DeleteNotesUseCase(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note:Note){
        notesRepository.delete(note)
    }
}
