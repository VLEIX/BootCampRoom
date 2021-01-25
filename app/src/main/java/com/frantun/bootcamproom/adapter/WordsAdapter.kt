package com.frantun.bootcamproom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcamproom.R
import com.frantun.bootcamproom.database.Word
import com.frantun.bootcamproom.interfaces.IOnEventListener

class WordsAdapter(private var items: List<Word>) : RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private var listener : IOnEventListener? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtWord: TextView = view.findViewById(R.id.item_text)

        fun bind(item: Word) {
            txtWord.text = item.word

            itemView.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }

    fun updateItems(items: List<Word>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setListener(listener : IOnEventListener) {
        this.listener = listener
    }
}