package com.example.mynotes

import android.app.Application
import androidx.room.Room
import com.example.mynotes.data.NotesDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NotesApplication: Application() {
    lateinit var notesDatabase: NotesDatabase
    private set

    override fun onCreate() {
        super.onCreate()
       /* notesDatabase = Room
            .databaseBuilder(this, NotesDatabase::class.java, "notes.db")
            .build()*/
    }
}