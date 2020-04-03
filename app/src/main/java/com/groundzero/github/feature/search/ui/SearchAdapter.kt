package com.groundzero.github.feature.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.github.databinding.ItemSearchBinding
import com.groundzero.github.feature.search.data.Repository

class SearchAdapter(private val listener: SearchListener) :
    ListAdapter<Repository, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val searchResponse: Repository = getItem(position)
        holder.bind(searchResponse)
    }

    class SearchViewHolder(
        private val binding: ItemSearchBinding,
        private val listener: SearchListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.repo = repository
            itemView.setOnClickListener { listener.onSearchItemClick(repository) }
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Repository, newItem: Repository
    ) = oldItem == newItem
}