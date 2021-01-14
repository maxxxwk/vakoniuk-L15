package com.maxxxwk.texthometask

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxxxwk.texthometask.databinding.ListItemBinding

class NamesListAdapter : ListAdapter<String, RecyclerView.ViewHolder>(DiffCallback()) {

    class NameViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            val spannableName = SpannableString(name)
            val lastName = name.split(" ")[1]
            val startIndex = name.indexOf(lastName)
            val endIndex = startIndex + lastName.length
            spannableName.setSpan(ForegroundColorSpan(Color.RED), startIndex, endIndex, 0)
            spannableName.setSpan(UnderlineSpan(), startIndex, endIndex, 0)
            binding.tvFullName.text = spannableName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NameViewHolder).bind(getItem(position))
    }
}