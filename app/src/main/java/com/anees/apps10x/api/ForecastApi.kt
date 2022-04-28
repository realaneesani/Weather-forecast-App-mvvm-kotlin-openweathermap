package com.anees.apps10x.api

import com.anees.apps10.model.forecast.ForecastResponse

interface ForecastApi {
    suspend fun getForecast(): ForecastResponse

}