package com.example.mynotes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotes.NotesApplication
import com.example.mynotes.NotesDatabase
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.detail.DetailActivity
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter


    //creamos una factori para recuperar los datos que le pasamos por parametro
    private val vm by viewModels<MainViewModel>{
        MainViewModelFactory(
            (application as NotesApplication).notesDatabase
        )
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

    override fun onResume() {
        super.onResume()
        vm.onResume()

    }

}