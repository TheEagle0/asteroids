package com.predator.theeagle.astroids.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.predator.theeagle.astroids.entities.Asteroid
import com.predator.theeagle.astroids.database.DatabaseClient
import com.predator.theeagle.astroids.entities.PictureOfTheDay
import com.predator.theeagle.astroids.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = DatabaseClient.getInstance(app)
    private val repository = AsteroidsRepository(database)
    val asteroids = repository.asteroids

    private val _pictureOfTheDay = MutableLiveData<PictureOfTheDay>()
    val pictureOfTheDay: LiveData<PictureOfTheDay>
        get() = _pictureOfTheDay

    private val _navigateToDetailFragment = MutableLiveData<Asteroid?>()
    val navigateToDetailFragment
        get() = _navigateToDetailFragment

    init {

            refreshAsteroids()
            getPictureOfTheDay()

    }

    fun onAsteroidItemClick(data: Asteroid) {
        _navigateToDetailFragment.value = data
    }

    fun onDetailFragmentNavigated() {
        _navigateToDetailFragment.value = null
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            try {
                _pictureOfTheDay.value = repository.getPictureOfTheDay()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}