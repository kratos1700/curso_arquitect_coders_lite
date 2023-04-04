package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentNotesUseCase(private val notesRepository: NotesRepository) {
  operator  fun invoke():Flow<List<Note>> = notesRepository.currentNotes

}
