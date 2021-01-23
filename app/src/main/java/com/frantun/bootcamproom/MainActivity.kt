package com.frantun.bootcamproom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcamproom.adapter.IOnEventListener
import com.frantun.bootcamproom.adapter.WordsAdapter
import com.frantun.bootcamproom.application.WordsApplication
import com.frantun.bootcamproom.database.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: WordViewModelFactory
    lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelFactory = WordViewModelFactory((application as WordsApplication).repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WordViewModel::class.java)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rccWords)
        val adapter = WordsAdapter(emptyList())
        adapter.setListener(object : IOnEventListener {
            override fun onClick(word: Word) {
                viewModel.delete(word)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fabAddWord = findViewById<FloatingActionButton>(R.id.fabAddWord)
        fabAddWord.setOnClickListener {
            val intent = Intent(this, AddWordActivity::class.java)
            startActivityForResult(intent, 1)
        }

        viewModel.allWords.observe(this, Observer { words ->
            words?.let {
                adapter.updateItems(words)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(AddWordActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(reply)
                viewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}