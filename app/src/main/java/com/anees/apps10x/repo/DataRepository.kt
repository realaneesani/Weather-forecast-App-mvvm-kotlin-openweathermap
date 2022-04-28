package com.anees.apps10x.repo

import com.anees.apps10.model.weather.WeatherResponse
import com.anees.apps10x.api.WeatherApi

class DataRepository( private val weatherApi: WeatherApi) {

    suspend fun getWeather(): WeatherResponse {
        return weatherApi.getWeather()
    }


}