package com.predator.theeagle.astroids.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.predator.theeagle.astroids.Constants


@Entity(tableName = Constants.TABLE_NAME)
data class AsteroidDataEntity(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean)


