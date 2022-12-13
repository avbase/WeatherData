package ru.avicorp.weatherdata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class WeatherViewModelFactory(var api: WeatherApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(api = api) as T
    }
}