package com.anees.apps10.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anees.apps10.model.weather.Main
import com.anees.apps10.model.weather.WeatherResponse
import com.anees.apps10x.repo.DataRepository
import com.anees.apps10x.retrofit.RetrofitInst

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel  : ViewModel() {

    private val dataRepository = DataRepository(RetrofitInst.service)


    val weatherLiveData = MutableLiveData<Main>()
    val cityLiveData = MutableLiveData<WeatherResponse>()


    fun fetchWeather(){
            viewModelScope.launch {
                val weather = withContext(Dispatchers.IO){
                    dataRepository.getWeather().main
                }
                weatherLiveData.value = weather
            }
    }

    fun fetchCity(){
        viewModelScope.launch {
            val city = withContext(Dispatchers.IO){
                dataRepository.getWeather()
            }
            cityLiveData.value = city
        }
    }



}