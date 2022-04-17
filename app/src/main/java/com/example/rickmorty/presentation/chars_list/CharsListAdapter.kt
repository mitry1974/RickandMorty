package com.example.rickmorty.presentation.chars_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.ItemCharListBinding
import com.example.rickmorty.domain.models.Character
import com.squareup.picasso.Picasso

class CharsListAdapter(private val onClickListener: (Character) -> Unit) :
    PagingDataAdapter<Character, CharsListAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickListener
        )
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem == newItem
    }

    class CharacterViewHolder(private val binding: ItemCharListBinding, private  val onClickListener: (Character) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character?) {
            item?.let {
                binding.charName.text = item.name.split(" ").joinToString("\n")
                binding.charGender.text = item.gender
                binding.charSpecies.text = item.species
                Picasso.Builder(binding.root.context).build().load(item.image)
                    .into(binding.charImage)
                binding.root.setOnClickListener {
                    onClickListener(item)
                }
            }
        }
    }
}