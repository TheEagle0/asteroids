package com.predator.theeagle.astroids.app

import android.app.Application
import android.os.Build
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MyApp : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    private val debug = false

    override fun onCreate() {
        super.onCreate()

        delayInit()
    }

    private fun delayInit() = applicationScope.launch {
        setupRecurringWork()
    }

    private fun setupRecurringWork() {

        val constraints: Constraints by lazy { workerConstraints() }

        val timeUnit = if(debug) TimeUnit.SECONDS else TimeUnit.DAYS
        val interval:Long = if(debug) 10 else 1

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshWorker>(interval, timeUnit)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            repeatingRequest)
    }

    private fun workerConstraints(): Constraints {
        return if (debug) {
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        } else {
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }.build()
        }
    }
}