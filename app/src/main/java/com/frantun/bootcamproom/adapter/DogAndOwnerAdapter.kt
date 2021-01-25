package com.frantun.bootcamproom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcamproom.database.DogAndOwner
import com.frantun.bootcamproom.databinding.RecyclerItemBinding

class DogAndOwnerAdapter(private val listener: DogAndOwnerListener) :
    ListAdapter<DogAndOwner, DogAndOwnerAdapter.ViewHolder>(DogAndOwnerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DogAndOwner, listener: DogAndOwnerListener) {
            binding.itemText.text = item.owner.name
            binding.root.setOnClickListener {
                listener.onClick(item)
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

class DogAndOwnerDiffCallback : DiffUtil.ItemCallback<DogAndOwner>() {
    override fun areItemsTheSame(oldItem: DogAndOwner, newItem: DogAndOwner): Boolean {
        return oldItem.owner.ownerId == newItem.owner.ownerId
    }

    override fun areContentsTheSame(oldItem: DogAndOwner, newItem: DogAndOwner): Boolean {
        return oldItem == newItem
    }
}

class DogAndOwnerListener(val clickListener: (dogAndOwner: DogAndOwner) -> Unit) {
    fun onClick(dogAndOwner: DogAndOwner) = clickListener(dogAndOwner)
}