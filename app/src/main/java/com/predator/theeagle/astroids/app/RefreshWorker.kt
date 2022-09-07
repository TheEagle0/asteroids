package com.predator.theeagle.astroids.app

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.predator.theeagle.astroids.database.DatabaseClient
import com.predator.theeagle.astroids.repository.AsteroidsRepository
import retrofit2.HttpException

class RefreshWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "REFRESH_DATA"
    }

    override suspend fun doWork(): Result {

        val database = DatabaseClient.getInstance(applicationContext)
        val repository = AsteroidsRepository(database)

        return try {
            repository.refreshAsteroids()
            Result.success()

        } catch (e: HttpException) {
            Result.retry()
        }
    }
}