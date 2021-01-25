package com.frantun.bootcamproom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcamproom.databinding.RecyclerItemBinding
import com.frantun.bootcamproom.model.RoomTopic

class RoomTopicsAdapter(private val listener: RoomTopicListener) :
    ListAdapter<RoomTopic, RoomTopicsAdapter.ViewHolder>(RoomTopicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RoomTopic, listener: RoomTopicListener) {
            binding.itemText.text = item.name
            binding.root.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RoomTopicDiffCallback : DiffUtil.ItemCallback<RoomTopic>() {
    override fun areItemsTheSame(oldItem: RoomTopic, newItem: RoomTopic): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RoomTopic, newItem: RoomTopic): Boolean {
        return oldItem == newItem
    }
}

class RoomTopicListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(position: Int) = clickListener(position)
}