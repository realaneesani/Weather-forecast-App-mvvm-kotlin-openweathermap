package com.anees.apps10x.retrofit

import com.anees.apps10.model.forecast.ForecastResponse
import com.anees.apps10.model.weather.WeatherResponse
import com.anees.apps10x.api.ForecastApi
import com.anees.apps10x.api.WeatherApi
import retrofit2.http.GET


interface RetrofitApi : WeatherApi, ForecastApi {

    @GET("weather?q=Bengaluru&units=metric&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
    override suspend fun getWeather(): WeatherResponse



    @GET("forecast?q=Bengaluru&units=metric&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
    override suspend fun getForecast(): ForecastResponse
}