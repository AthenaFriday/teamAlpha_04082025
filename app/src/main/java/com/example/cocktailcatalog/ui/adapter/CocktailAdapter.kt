package com.example.cocktailcatalog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailcatalog.databinding.ItemCocktailBinding
import com.example.cocktailcatalog.model.Cocktail

class CocktailAdapter(private val onClick: (Cocktail) -> Unit) :
    ListAdapter<Cocktail, CocktailAdapter.CocktailViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CocktailViewHolder(private val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: Cocktail) {
            binding.tvCocktailName.text = cocktail.name
            Glide.with(binding.ivCocktail.context)
                .load(cocktail.thumbnail)
                .into(binding.ivCocktail)
            binding.root.setOnClickListener { onClick(cocktail) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Cocktail>() {
        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean =
            oldItem == newItem
    }
}
