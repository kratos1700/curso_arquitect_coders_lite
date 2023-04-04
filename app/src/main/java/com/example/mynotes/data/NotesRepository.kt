package com.example.mynotes.data

import com.example.mynotes.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDataSource: NotesLocalDataSource) {

    val currentNotes: Flow<List<Note>> = notesDataSource.currentNotes

    suspend fun delete(nota: Note) {
        notesDataSource.delete(nota)
    }

    suspend fun getById(noteId: Int): Note? = notesDataSource.getById(noteId)

    suspend fun save(nota: Note) {
        notesDataSource.insert(nota)
    }


}
