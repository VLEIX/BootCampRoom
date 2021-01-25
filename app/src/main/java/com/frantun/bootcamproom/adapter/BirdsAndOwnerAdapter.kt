package com.frantun.bootcamproom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcamproom.database.BirdsAndOwners
import com.frantun.bootcamproom.database.DogAndOwner
import com.frantun.bootcamproom.databinding.RecyclerItemBinding

class BirdsAndOwnerAdapter(private val listener: BirdsAndOwnerListener) :
    ListAdapter<BirdsAndOwners, BirdsAndOwnerAdapter.ViewHolder>(BirdsAndOwnerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BirdsAndOwners, listener: BirdsAndOwnerListener) {
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

class BirdsAndOwnerDiffCallback : DiffUtil.ItemCallback<BirdsAndOwners>() {
    override fun areItemsTheSame(oldItem: BirdsAndOwners, newItem: BirdsAndOwners): Boolean {
        return oldItem.owner.ownerId == newItem.owner.ownerId
    }

    override fun areContentsTheSame(oldItem: BirdsAndOwners, newItem: BirdsAndOwners): Boolean {
        return oldItem == newItem
    }
}

class BirdsAndOwnerListener(val clickListener: (birdsAndOwners: BirdsAndOwners) -> Unit) {
    fun onClick(birdsAndOwners: BirdsAndOwners) = clickListener(birdsAndOwners)
}