package com.example.mynotes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.mynotes.NotesApplication
import com.example.mynotes.NotesDatabase
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.detail.DetailActivity
import kotlinx.coroutines.launch

lateinit var binding: ActivityMainBinding
lateinit var notesAdapter: NotesAdapter
lateinit var database: NotesDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = (application as NotesApplication).notesDatabase
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = {
                    lifecycleScope.launch {
                        database.notesDao().delete(it)
                        notesAdapter.submitList(database.notesDao().getAll())
                    }

                }

            )
            notesList.adapter = notesAdapter

            fab.setOnClickListener {

                DetailActivity.navigate(this@MainActivity)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val notes = database.notesDao().getAll()
            notesAdapter.submitList(notes)
        }
    }

}