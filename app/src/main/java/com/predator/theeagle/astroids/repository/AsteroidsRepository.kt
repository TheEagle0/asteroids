package com.predator.theeagle.astroids.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.predator.theeagle.astroids.Constants
import com.predator.theeagle.astroids.DateUtil
import com.predator.theeagle.astroids.entities.Asteroid
import com.predator.theeagle.astroids.api.ApiManager
import com.predator.theeagle.astroids.api.asAsteroidEntities
import com.predator.theeagle.astroids.database.DatabaseClient
import com.predator.theeagle.astroids.database.asAsteroids
import com.predator.theeagle.astroids.entities.PictureOfTheDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository(private val database: DatabaseClient) {

    fun filterAsteroids(filter: String): LiveData<List<Asteroid>> {
        return when (filter) {
            Constants.ALL_ASTEROIDS -> {
                Transformations.map(database.dao.getAll()) {
                    it.asAsteroids()
                }
            }
            Constants.TODAY_ASTEROIDS -> {
                Transformations.map(database.dao.getToday(DateUtil.todayDate())) {
                    it.asAsteroids()
                }
            }
            else -> {
                Transformations.map(database.dao.getWeek(DateUtil.todayDate())) {
                    it.asAsteroids()
                }
            }
        }
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroids = ApiManager.getAsteroids()
            database.dao.insertAll(asteroids.asAsteroidEntities())
        }
    }

    suspend fun getPictureOfTheDay(): PictureOfTheDay {
        lateinit var pictureOfTheDay: PictureOfTheDay
        withContext(Dispatchers.IO) {
            pictureOfTheDay = ApiManager.getPictureOfTheDay()
        }
        return pictureOfTheDay
    }
}