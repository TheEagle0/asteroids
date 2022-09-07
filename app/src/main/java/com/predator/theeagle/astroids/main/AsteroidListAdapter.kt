package com.predator.theeagle.astroids.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.predator.theeagle.astroids.entities.Asteroid

class AsteroidListAdapter(private val itemClickListener: AsteroidItemClickListener)
    : ListAdapter<Asteroid, AsteroidViewHolder>(AsteroidDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }
}