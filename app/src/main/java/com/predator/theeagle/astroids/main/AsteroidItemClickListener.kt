package com.predator.theeagle.astroids.main

import com.predator.theeagle.astroids.entities.Asteroid

class AsteroidItemClickListener(val clickListener: (Asteroid) -> Unit) {
    fun onClick(data: Asteroid) = clickListener(data)
}