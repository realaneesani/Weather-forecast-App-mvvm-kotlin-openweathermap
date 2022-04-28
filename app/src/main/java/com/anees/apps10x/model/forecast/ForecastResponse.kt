package com.anees.apps10.model.forecast

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Data>,
    val message: Int
)