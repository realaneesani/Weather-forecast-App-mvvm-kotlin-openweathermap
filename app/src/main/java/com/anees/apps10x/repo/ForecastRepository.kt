package com.anees.apps10x.repo

import com.anees.apps10.model.forecast.ForecastResponse
import com.anees.apps10x.api.ForecastApi

class ForecastRepository( private val forecastApi: ForecastApi) {

    suspend fun getForecast(): ForecastResponse {
        return forecastApi.getForecast()
    }
}