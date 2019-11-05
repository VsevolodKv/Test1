package com.example.web.weekFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.web.dataclasess.WeatherDetails
import com.example.web.model.WebModel

class WeekFragmentViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val weekForecast = MutableLiveData<WeatherDetails>()

    init {
        isLoading.value = false
    }


    internal fun refresh() {
        isLoading.postValue(true)
        WebModel.getDataString(object : WebModel.ResponseResult {
            override fun onFetchResult(result: WeatherDetails) {
                weekForecast.postValue(result)
                isLoading.postValue(false)
            }
        })
    }

}