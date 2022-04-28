package com.anees.apps10x.api

import com.anees.apps10.model.weather.WeatherResponse

interface WeatherApi {

    suspend fun getWeather() : WeatherResponse
}