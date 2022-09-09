package com.predator.theeagle.astroids.main

import android.app.Application
import androidx.lifecycle.*
import com.predator.theeagle.astroids.Constants
import com.predator.theeagle.astroids.entities.Asteroid
import com.predator.theeagle.astroids.database.DatabaseClient
import com.predator.theeagle.astroids.entities.PictureOfTheDay
import com.predator.theeagle.astroids.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = DatabaseClient.getInstance(app)
    private val repository = AsteroidsRepository(database)
    private val liveSelectedFilter = MutableLiveData<String>()

    val asteroids = Transformations.switchMap(liveSelectedFilter) {
        when (it) {
            Constants.ALL_ASTEROIDS -> repository.filterAsteroids(Constants.ALL_ASTEROIDS)
            Constants.TODAY_ASTEROIDS -> repository.filterAsteroids(Constants.TODAY_ASTEROIDS)
            else -> repository.filterAsteroids(Constants.WEEK_ASTEROIDS)
        }
    }

    fun setFilter(filter: String) {
        liveSelectedFilter.postValue(filter)
    }

    private val _pictureOfTheDay = MutableLiveData<PictureOfTheDay>()
    val pictureOfTheDay: LiveData<PictureOfTheDay>
        get() = _pictureOfTheDay

    private val _navigateToDetailFragment = MutableLiveData<Asteroid?>()
    val navigateToDetailFragment
        get() = _navigateToDetailFragment

    init {
        liveSelectedFilter.postValue("all")
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