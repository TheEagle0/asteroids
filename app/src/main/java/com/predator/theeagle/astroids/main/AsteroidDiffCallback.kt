package com.predator.theeagle.astroids.main

import androidx.recyclerview.widget.DiffUtil
import com.predator.theeagle.astroids.entities.Asteroid

class AsteroidDiffCallback: DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}
