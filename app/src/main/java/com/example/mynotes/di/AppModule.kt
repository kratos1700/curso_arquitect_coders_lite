package com.example.mynotes.di

import android.app.Application
import androidx.room.Room
import com.example.mynotes.data.NotesDatabase
import com.example.mynotes.data.NotesLocalDataSource
import com.example.mynotes.data.NotesRoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application) = Room.databaseBuilder(
        app,
        NotesDatabase::class.java,
        "notes.db"
    ).build()

    @Provides
    fun provideNoteDao(db: NotesDatabase) = db.notesDao()

    @Provides
    fun provideNotesLocalDataSource(notesRoomDataSource: NotesRoomDataSource): NotesLocalDataSource =
        notesRoomDataSource
}