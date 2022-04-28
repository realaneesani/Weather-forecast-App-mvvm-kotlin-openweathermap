package com.anees.apps10x

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.anees.apps10.viewModel.ForecastViewModel
import com.anees.apps10.viewModel.MainViewModel
import com.anees.apps10x.databinding.ActivityMainBinding
import com.anees.apps10x.repo.DataRepository
import com.anees.apps10x.retrofit.RetrofitInst
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataRepository = DataRepository(RetrofitInst.service)


    private lateinit var viewModel: MainViewModel
    private lateinit var fModel: ForecastViewModel
    private lateinit var connectivityManager: ConnectivityManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        fModel = ViewModelProvider(this)[ForecastViewModel()::class.java]
        val clk_rotate = AnimationUtils.loadAnimation(
            this,
            R.anim.rotate)
        binding.loading.startAnimation(clk_rotate)
        Handler().postDelayed({


            if(checkForInternet(this)){

                viewModel.weatherLiveData.observe(this){
                    binding.tempature.text = it.temp.toString()

                }
                viewModel.fetchWeather()
                if(binding.tempature.equals("")){
                    binding.floadingScreen.visibility = View.GONE
                    binding.fErrorScreen.visibility = View.VISIBLE
                }else {
                    binding.floadingScreen.visibility = View.GONE
                    binding.fWetherScreen.visibility = View.VISIBLE
                    loadData()
                }
            }else{
                binding.floadingScreen.visibility = View.GONE
                binding.fErrorScreen.visibility = View.VISIBLE


            }



        }, 2500)


        binding.retry.setOnClickListener() {

            if (checkForInternet(this)) {
                loadData()
            } else {


            }
        }





    }

    private fun  loadData(){
        binding.floadingScreen.visibility = View.GONE
        binding.fWetherScreen.visibility = View.VISIBLE
        viewModel.weatherLiveData.observe(this){
            binding.tempature.text = it.temp.toString()+"°"

        }
        viewModel.fetchWeather()

        viewModel.cityLiveData.observe(this){
            binding.city.text = it.name.toString()

        }
        viewModel.fetchCity()

        fModel.forecast1.observe(this){

            val simpleDateFormat = SimpleDateFormat("EEEE")

            val f = it.dt.toLong()
            val ms = f*1000

            val dateString = simpleDateFormat.format(ms)
            binding.day1.text = dateString
            binding.temp1.text = it.main.temp.toString()+" C"

        }
        fModel.forecast2.observe(this){

            val simpleDateFormat = SimpleDateFormat("EEEE")

            val f = it.dt.toLong()
            val ms = f*1000

            val dateString = simpleDateFormat.format(ms)
            binding.day2.text = dateString
            binding.temp2.text = it.main.temp.toString()+" C"

        }
        fModel.forecast3.observe(this){

            val simpleDateFormat = SimpleDateFormat("EEEE")

            val f = it.dt.toLong()
            val ms = f*1000

            val dateString = simpleDateFormat.format(ms)
            binding.day3.text = dateString
            binding.temp3.text = it.main.temp.toString()+" C"

        }
        fModel.forecast4.observe(this){

            val simpleDateFormat = SimpleDateFormat("EEEE")

            val f = it.dt.toLong()
            val ms = f*1000

            val dateString = simpleDateFormat.format(ms)
            binding.day4.text = dateString
            binding.temp4.text = it.main.temp.toString()+" C"

        }
        fModel.fetchList()
    }
    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}