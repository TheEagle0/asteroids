package com.predator.theeagle.astroids.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.predator.theeagle.astroids.databinding.AstroidItemBinding
import com.predator.theeagle.astroids.entities.Asteroid

class AsteroidViewHolder private constructor(private val binding: AstroidItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Asteroid, clickListener: AsteroidItemClickListener) {
        binding.asteroid = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup) : AsteroidViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = AstroidItemBinding.inflate(layoutInflater, parent, false)
            return AsteroidViewHolder(binding)
        }
    }
}