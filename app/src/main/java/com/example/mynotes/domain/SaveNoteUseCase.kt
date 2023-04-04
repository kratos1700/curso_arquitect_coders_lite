package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRepository

class SaveNoteUseCase(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note){
        notesRepository.save(note)
    }
}