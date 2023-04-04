package com.example.mynotes.data

import com.example.mynotes.Note
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {
    val currentNotes: Flow<List<Note>>

    suspend fun delete(nota: Note)

    suspend fun getById(noteId: Int): Note?

    suspend fun insert(nota: Note)
}

class NotesRoomDataSource(private val noteDao: NoteDao) : NotesLocalDataSource {

    override val currentNotes: Flow<List<Note>> = noteDao.getAll()

    override suspend fun delete(nota: Note) {

        noteDao.delete(nota)
    }

    override suspend fun getById(noteId: Int): Note? = noteDao.getById(noteId)


    override suspend fun insert(nota: Note) {
        noteDao.insert(nota)

    }


}