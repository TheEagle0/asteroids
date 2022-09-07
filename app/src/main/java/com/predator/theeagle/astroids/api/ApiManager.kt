package com.predator.theeagle.astroids.api

import com.predator.theeagle.astroids.entities.Asteroid
import org.json.JSONObject

object ApiManager {
    private val retrofitService : ApiService by lazy {
        ApiClient.retrofit.create(ApiService::class.java)
    }

    suspend fun getAsteroids() : List<Asteroid> {
        val responseStr = retrofitService.getAsteroids()
        val responseJsonObject = JSONObject(responseStr)

        return parseAsteroidsJsonResult(responseJsonObject)
    }

    suspend fun getPictureOfTheDay() = retrofitService.getPictureOfTheDay()
}
