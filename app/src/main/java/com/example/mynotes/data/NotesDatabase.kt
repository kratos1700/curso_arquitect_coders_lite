package com.example.mynotes.data

import androidx.room.*
import com.example.mynotes.Note
import kotlinx.coroutines.flow.Flow


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase :RoomDatabase(){
    abstract fun notesDao(): NoteDao
}

@Dao
interface NoteDao{
    //modificado para optimizar con flows
    @Query("SELECT * FROM note")
     fun getAll() : Flow<List<Note>>
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getById(id:Int) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

}