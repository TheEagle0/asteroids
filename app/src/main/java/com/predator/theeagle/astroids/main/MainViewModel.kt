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

    private val mockData = false
    private val _mockAsteroids = MutableLiveData<List<Asteroid>>()
    val mockAsteroids : LiveData<List<Asteroid>>
        get() = _mockAsteroids

    init {
        if(mockData) {
            mockData()
        } else {
            refreshAsteroids()
            getpictureOfTheDay()
        }
    }

    private fun mockData() {

        val dataList = mutableListOf<Asteroid>()

        var count = 1
        while (count <= 10) {

            val data = Asteroid(
                count.toLong(),
                "codename:$count",
                "XXXX-XX-XX",
                77.0,
                88.0,
                99.8,
                66.6,
                true)

            dataList.add(data)

            ++count
        }

        _mockAsteroids.postValue(dataList)
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

    private fun getpictureOfTheDay() {
        viewModelScope.launch {
            try {
                _pictureOfTheDay.value = repository.getPictureOfTheDay()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}