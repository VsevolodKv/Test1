package com.example.web.today

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.example.web.dataObject.Fact
import com.example.web.model.WebModel

class TodayFragmentViewModel(app: Application) : AndroidViewModel(app) {

    val isLoading = MutableLiveData<Boolean>()
    val factSubscription = MutableLiveData<Fact>()
    private val model = WebModel()
    private val mainHandler = Handler(Looper.getMainLooper())

    init {
        isLoading.value = false
    }

    internal fun refresh(lat: Double, lng: Double) {
        isLoading.postValue(true)
        model.getWeatherAsync (lat, lng) { data ->
            mainHandler.post {
                isLoading.postValue(false)
                data?.let {
                    factSubscription.postValue(it.fact)
                    return@post
                }
                Toast.makeText(getApplication(), "no data", Toast.LENGTH_LONG).show()
            }
        }
    }

}
