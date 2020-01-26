package com.example.web.model

import com.example.web.dataObject.WeatherRespons
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class WebModel {
    fun getWeatherAsync(lat: Double, lng: Double, callback: (data: WeatherRespons?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.weather.yandex.ru/v1/forecast?lat=$lat&lon=$lng&lang=ru_RU&limit=1&hours=false&extra=false")
            .get()
            .addHeader("X-Yandex-API-Key", "30471ca0-62dc-4fc0-8e89-d4f9f449dd58")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()?.string() ?: ""
                try {
                    val res = Gson().fromJson(responseBody, WeatherRespons::class.java)
                    callback(res)
                } catch (e: Exception) {
                    callback(null)
                }
            }
        })
    }
}
