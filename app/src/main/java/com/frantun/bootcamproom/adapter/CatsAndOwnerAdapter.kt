package com.frantun.bootcamproom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcamproom.database.CatsAndOwner
import com.frantun.bootcamproom.database.DogAndOwner
import com.frantun.bootcamproom.databinding.RecyclerItemBinding

class CatsAndOwnerAdapter(private val listener: CatsAndOwnerListener) :
    ListAdapter<CatsAndOwner, CatsAndOwnerAdapter.ViewHolder>(CatsAndOwnerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CatsAndOwner, listener: CatsAndOwnerListener) {
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

class CatsAndOwnerDiffCallback : DiffUtil.ItemCallback<CatsAndOwner>() {
    override fun areItemsTheSame(oldItem: CatsAndOwner, newItem: CatsAndOwner): Boolean {
        return oldItem.owner.ownerId == newItem.owner.ownerId
    }

    override fun areContentsTheSame(oldItem: CatsAndOwner, newItem: CatsAndOwner): Boolean {
        return oldItem == newItem
    }
}

class CatsAndOwnerListener(val clickListener: (catsAndOwner: CatsAndOwner) -> Unit) {
    fun onClick(catsAndOwner: CatsAndOwner) = clickListener(catsAndOwner)
}