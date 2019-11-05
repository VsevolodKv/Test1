package com.example.web.model

import com.ahmadrosid.svgloader.SvgLoader
import com.example.web.dataclasess.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.contracts.contract

object WebModel {

    private var viewModelCallback: ResponseResult? = null

    private val onDataReadyCallback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
        }

        override fun onResponse(call: Call, response: Response) {

            val responseBody = response.body()?.string() ?: ""
            if (responseBody.isNotEmpty()) {
                viewModelCallback?.onFetchResult(parseJson(responseBody))
            }
        }
    }

    private fun parseJson(responseBody: String): WeatherDetails {

        val json = JSONObject(responseBody)

        var temp = ""
        var windSpeed = ""
        var feelsLike = ""
        var icon = ""
        var humidity = ""

        if (json.has("fact")) {

            val factJson = json.getJSONObject("fact")

            temp = factJson.getString("temp")
            windSpeed = factJson.getString("wind_speed")
            feelsLike = factJson.getString("feels_like")
            icon = factJson.getString("icon")
            humidity = factJson.getString("humidity")
        }
        val fact = Fact(temp, windSpeed, feelsLike, icon, humidity)

        val forecasts = mutableListOf<WeatherDetailsByDay>()

        if (json.has("forecasts")) {

            val forecast = json.getJSONArray("forecasts")

            for (i in 0 until forecast.length()) {

                val day = forecast.getJSONObject(i)

                var humidityDaytime = ""
                var windSpeedDaytime = ""
                var feelsLikeDaytime = ""
                var tempMinDaytime = ""
                var tempMaxDaytime = ""
                var iconViewDaytime = ""
                var tempAvgDaytime = ""
                var conditionDaytime = ""
                var windGustDaytime = ""
                var pressureMmDaytime = ""
                var pressurePaDaytime = ""
                var precMmDaytime = ""
                var precPeriodDaytime = ""
                var precProbDaytime = ""

                var humidityNight = ""
                var windSpeedNight = ""
                var feelsLikeNight = ""
                var tempMinNight = ""
                var tempMaxNight = ""
                var iconViewNight = ""
                var tempAvgNight = ""
                var conditionNight = ""
                var windGustNight = ""
                var pressureMmNight = ""
                var pressurePaNight = ""
                var precMmNight = ""
                var precPeriodNight = ""
                var precProbNight = ""


                val date = day.getString("date")

                if (day.has("parts")) {

                    val parts = day.getJSONObject("parts")

                    val byDay = parts.getJSONObject("day")
                    humidityDaytime = byDay.getString("icon")
                    windSpeedDaytime = byDay.getString("temp_min")
                    feelsLikeDaytime = byDay.getString("temp_max")
                    tempMinDaytime = byDay.getString("wind_speed")
                    tempMaxDaytime = byDay.getString("feels_like")
                    iconViewDaytime = byDay.getString("temp_avg")
                    tempAvgDaytime = byDay.getString("condition")
                    conditionDaytime = byDay.getString("wind_speed")
                    windGustDaytime = byDay.getString("wind_gust")
                    pressureMmDaytime = byDay.getString("pressure_mm")
                    pressurePaDaytime = byDay.getString("pressure_pa")
                    precMmDaytime = byDay.getString("prec_mm")
                    precPeriodDaytime = byDay.getString("prec_period")
                    precProbDaytime = byDay.getString("prec_prob")

                    val night = parts.getJSONObject("night")
                    humidityNight = night.getString("temp_min")
                    windSpeedNight = night.getString("icon")
                    feelsLikeNight = night.getString("temp_max")
                    tempMinNight = night.getString("wind_speed")
                    tempMaxNight = night.getString("humidity")
                    iconViewNight = night.getString("feels_like")
                    tempAvgNight = night.getString("temp_avg")
                    conditionNight = night.getString("condition")
                    windGustNight = night.getString("wind_gust")
                    pressureMmNight = night.getString("pressure_mm")
                    pressurePaNight = night.getString("pressure_pa")
                    precMmNight = night.getString("prec_mm")
                    precPeriodNight = night.getString("prec_period")
                    precProbNight = night.getString("prec_prob")

                }
                val dayInfo = DayInfo(
                    Daytime(
                        humidityDaytime,
                        windSpeedDaytime,
                        feelsLikeDaytime,
                        tempMinDaytime,
                        tempMaxDaytime,
                        iconViewDaytime,
                        tempAvgDaytime,
                        conditionDaytime,
                        windGustDaytime,
                        pressureMmDaytime,
                        pressurePaDaytime,
                        precMmDaytime,
                        precPeriodDaytime,
                        precProbDaytime),
                    Night(
                        humidityNight,
                        windSpeedNight,
                        feelsLikeNight,
                        tempMinNight,
                        tempMaxNight,
                        iconViewNight,
                        tempAvgNight,
                        conditionNight,
                        windGustNight,
                        pressureMmNight,
                        pressurePaNight,
                        precMmNight,
                        precPeriodNight,
                        precProbNight)
                )
                val weatherDetailsByDay = WeatherDetailsByDay(date, dayInfo)
                forecasts.add(weatherDetailsByDay)
            }
        }
        return WeatherDetails(fact, forecasts)
    }

    fun getDataString(callback: ResponseResult) {
        viewModelCallback = callback
        getWeatherAsync()
    }

    private fun getWeatherAsync() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.weather.yandex.ru/v1/forecast?lat=52.4345000&lon=30.9754000&lang=ru_RU&limit=1&hours=folse&extra=folse")
            .get()
            .addHeader("X-Yandex-API-Key", "0f4a6da2-493f-4f3a-8ec2-ebf9218febf4")
            .build()
        client.newCall(request).enqueue(onDataReadyCallback)
    }

    interface ResponseResult {
        fun onFetchResult(result: WeatherDetails)
    }
}