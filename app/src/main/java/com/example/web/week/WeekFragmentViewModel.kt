package com.example.web.week

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.web.dataObject.WeatherRespons
import com.example.web.model.WebModel

class WeekFragmentViewModel(app: Application) : AndroidViewModel(app) {
    val isLoading = MutableLiveData<Boolean>()
    val weekForecast = MutableLiveData<WeatherRespons>()
    private val model = WebModel()

    init {
        isLoading.value = false
    }


    internal fun refresh(lat: Double, lng: Double) {
        isLoading.postValue(true)
        model.getWeatherAsync(lat, lng) { data ->
            data?.let {
                weekForecast.postValue(it)
                isLoading.postValue(false)
                return@getWeatherAsync
            }
            Toast.makeText(getApplication(), "no data", Toast.LENGTH_LONG).show()
        }
    }

}