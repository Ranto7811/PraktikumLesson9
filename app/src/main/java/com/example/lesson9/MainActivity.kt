package com.example.lesson9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitha.roomwordsample.R

class MainActivity : AppCompatActivity() {
    private val newWordActivityRequestCode = 1
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel.allWords.observe(this,{ words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        })
    }
}