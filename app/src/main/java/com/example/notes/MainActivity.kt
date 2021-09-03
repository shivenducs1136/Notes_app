package com.example.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRvAdapter {

    lateinit var viewModel: NoteViewModel
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val view = binding.root
        val adapter = NotesRVAdapter(this,this)
        val recyclerView = binding.recview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list ->

            list?.let{
                adapter.updatelist(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {

        viewModel.deleteNode(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()

    }

    fun submitbtnclicked(view: View) {
        val notetext = binding.mainEditTxt.text.toString()
        if(notetext.isNotEmpty()){
            viewModel.insertNote(Note(notetext))
            Toast.makeText(this,"$notetext added",Toast.LENGTH_LONG).show()

        }

    }


}