package com.predator.theeagle.astroids.api

import com.predator.theeagle.astroids.Constants
import com.predator.theeagle.astroids.entities.PictureOfTheDay
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.HTTP_GET_NEO_FEED_PATH)
    suspend fun getAsteroids(
        @Query(Constants.QUERY_START_DATE_PARAM) startDate: String="",
        @Query(Constants.QUERY_END_DATE_PARAM) endDate: String="",
        @Query(Constants.QUERY_API_KEY_PARAM) apiKey: String="aoyjs19T0u9ckFNvEHkzX4ue1yku4CcgwA2HAmWr"): String

    @GET(Constants.HTTP_GET_APOD_PATH)
    suspend fun getPictureOfTheDay(@Query(Constants.QUERY_API_KEY_PARAM) apiKey:String="aoyjs19T0u9ckFNvEHkzX4ue1yku4CcgwA2HAmWr") : PictureOfTheDay
}