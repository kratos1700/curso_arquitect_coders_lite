package com.example.mynotes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotes.NotesApplication
import com.example.mynotes.data.NotesRoomDataSource
import com.example.mynotes.data.NotesRepository
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.ui.detail.DetailActivity
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter


    //creamos una factori para recuperar los datos que le pasamos por parametro
    private val vm by viewModels<MainViewModel>{
        val notesDatabase =(application as NotesApplication).notesDatabase
        val notesRoomDataSource = NotesRoomDataSource(notesDatabase.notesDao())
        MainViewModelFactory(NotesRepository(notesRoomDataSource))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = {
                    vm.onNoteDelete(it) }

            )
            notesList.adapter = notesAdapter

            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }
            //observamos los cambios del estado
            lifecycleScope.launch{
                // para evitar errores especificar cuendo se tiene que suscribir para recuperar datos
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect{
                        notesAdapter.submitList(it)
                    }
                }
            }
        }

    }



}