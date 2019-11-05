package com.example.web.today

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.web.model.WebModel
import com.example.web.dataclasess.Fact
import com.example.web.dataclasess.WeatherDetails
import com.example.web.dataclasess.WeatherDetailsByDay
import org.json.JSONObject

class TodayViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val factSubscription = MutableLiveData<Fact>()
    val weekForecast = MutableLiveData<WeatherDetails>()

    init {
        isLoading.value = false
    }


    internal fun refresh() {
        isLoading.postValue(true)
        WebModel.getDataString(object : WebModel.ResponseResult {
            override fun onFetchResult(result: WeatherDetails) {
                factSubscription.postValue(result.fact)
                weekForecast.postValue(result)
                isLoading.postValue(false)
            }
        })
    }
}
