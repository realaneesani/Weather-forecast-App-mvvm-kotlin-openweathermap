package com.anees.apps10.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anees.apps10.model.forecast.Data
import com.anees.apps10.model.forecast.ForecastResponse
import com.anees.apps10x.repo.ForecastRepository
import com.anees.apps10x.retrofit.RetrofitInst

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastViewModel : ViewModel() {

    private val forecastRepository = ForecastRepository(RetrofitInst.service)

    val forecast1 = MutableLiveData<Data>()

    val forecast2 = MutableLiveData<Data>()

    val forecast3 = MutableLiveData<Data>()

    val forecast4 = MutableLiveData<Data>()


    fun fetchList(){

        viewModelScope.launch {
            val forec = withContext(Dispatchers.IO){
                forecastRepository.getForecast().list[9]
            }
            forecast1.value = forec
        }

        viewModelScope.launch {
            val forec = withContext(Dispatchers.IO){
                forecastRepository.getForecast().list[17]
            }
            forecast2.value = forec
        }

        viewModelScope.launch {
            val forec = withContext(Dispatchers.IO){
                forecastRepository.getForecast().list[25]
            }
            forecast3.value = forec
        }

        viewModelScope.launch {
            val forec = withContext(Dispatchers.IO){
                forecastRepository.getForecast().list[34]
            }
            forecast4.value = forec
        }


    }



}