package ru.avicorp.weatherdata.presentation

import CityWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("id")id:Long,
        @Query("units")units:String
    ):CityWeatherResponse
}