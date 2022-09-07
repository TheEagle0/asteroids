package com.predator.theeagle.astroids.database

import com.predator.theeagle.astroids.entities.Asteroid
import com.predator.theeagle.astroids.entities.AsteroidDataEntity

fun List<AsteroidDataEntity>.asAsteroids() : List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}