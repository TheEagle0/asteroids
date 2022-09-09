package com.predator.theeagle.astroids.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.predator.theeagle.astroids.Constants
import com.predator.theeagle.astroids.entities.AsteroidDataEntity

@Dao
interface Dao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER by closeApproachDate")
    fun getAll(): LiveData<List<AsteroidDataEntity>>

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE closeApproachDate = :date ORDER by closeApproachDate")
    fun getToday(date: String): LiveData<List<AsteroidDataEntity>>

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE closeApproachDate > :date ORDER by closeApproachDate")
    fun getWeek(date: String): LiveData<List<AsteroidDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidDataEntity>)
}