package ru.avicorp.weatherdata.presentation

import CityWeatherResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val CITY_ID = 496638L//Sergiev Posad
private const val UNITS = "metric"

class WeatherViewModel(private val api:WeatherApi): ViewModel() {
    private val mData = MutableLiveData<CityWeatherResponse>()
    val data: LiveData<CityWeatherResponse> = mData

    init {
        loadWeatherData()
    }

    private fun loadWeatherData(){
        viewModelScope.launch {
            try {
                mData.value = api.getWeather(CITY_ID, UNITS)
            }catch (error: Throwable){
                error.printStackTrace()
            }
        }
    }

    fun loadWeather(){
        loadWeatherData()
    }
}