package com.predator.theeagle.astroids.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.predator.theeagle.astroids.Constants
import com.predator.theeagle.astroids.entities.AsteroidDataEntity

@Database(version = 1,entities = [AsteroidDataEntity::class])
abstract class DatabaseClient : RoomDatabase() {

    abstract val dao: Dao

    companion object {
        @Volatile
        private lateinit var instance: DatabaseClient

        fun getInstance(context: Context): DatabaseClient {
            synchronized(DatabaseClient::class.java) {
                if(!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseClient::class.java,
                        Constants.DATABASE_FILE_NAME)
                        .build()
                }
            }
            return instance
        }
    }
}
