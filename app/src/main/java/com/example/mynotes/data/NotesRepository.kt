package com.example.mynotes.data

import com.example.mynotes.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val noteDao: NoteDao) {

    val currentNotes: Flow<List<Note>> = noteDao.getAll()

    suspend fun delete(nota: Note) {
        noteDao.delete(nota)
    }

    suspend fun getById(noteId: Int): Note? = noteDao.getById(noteId)

    suspend fun save(nota: Note) {
        noteDao.insert(nota)
    }


}
