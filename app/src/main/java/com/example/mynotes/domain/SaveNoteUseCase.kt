package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note){
        notesRepository.save(note)
    }
}