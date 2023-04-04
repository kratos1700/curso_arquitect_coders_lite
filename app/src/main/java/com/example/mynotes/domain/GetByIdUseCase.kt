package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRepository
import javax.inject.Inject

class GetByIdUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(noteId: Int) = notesRepository.getById(noteId)
}