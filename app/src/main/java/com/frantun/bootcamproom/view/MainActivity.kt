package com.frantun.bootcamproom.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.frantun.bootcamproom.adapter.RoomTopicListener
import com.frantun.bootcamproom.adapter.RoomTopicsAdapter
import com.frantun.bootcamproom.databinding.ActivityMainBinding
import com.frantun.bootcamproom.model.RoomTopic
import com.frantun.bootcamproom.view.basic.WordsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var roomTopicsAdapter: RoomTopicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomTopicsAdapter = RoomTopicsAdapter(RoomTopicListener { position ->
            when (position) {
                0 -> startActivity(Intent(this, WordsActivity::class.java))
                1 -> {

                }
            }
        })
        binding.roomTopicsRecycler.apply {
            adapter = roomTopicsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        updateRoomTopics(listOf(RoomTopic("Basic"), RoomTopic("Advanced")))
    }

    private fun updateRoomTopics(roomTopicsList: List<RoomTopic>) {
        roomTopicsAdapter.submitList(roomTopicsList)
    }
}