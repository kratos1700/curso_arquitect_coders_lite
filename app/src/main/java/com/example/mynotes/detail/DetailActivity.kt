package com.example.mynotes.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.mynotes.Note
import com.example.mynotes.NotesApplication
import com.example.mynotes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE_ID = "note_id"

        fun navigate(activity: AppCompatActivity, noteId: Int = -1) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, noteId)
            }
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)

            lifecycleScope.launch {
                val database = (application as NotesApplication).notesDatabase
                val note = database.notesDao().getById(intent.getIntExtra(EXTRA_NOTE_ID, -1))



                if (note != null) {
                    etTitle.setText(note.title)
                    etDescription.setText(note.description)
                }
                btSave.setOnClickListener {
                    val title = etTitle.text.toString()
                    val descripcion = etDescription.text.toString()
                    lifecycleScope.launch {
                        if (note != null) {
                            database.notesDao().update(
                                note.copy(
                                    title = title,
                                    description = descripcion
                                )
                            )
                        } else {
                            database.notesDao().insert(
                                Note(0,title = title, description = descripcion)
                            )
                        }
                        finish()
                    }
                }


            }
        }
    }
}
