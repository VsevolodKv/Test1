package com.example.web

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.web.dataclasess.DayInfo
import com.example.web.dataclasess.Fact
import com.example.web.dataclasess.WeatherDetails
import com.example.web.dataclasess.WeatherDetailsByDay
import kotlinx.android.synthetic.main.fragment_week.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (isOnline()) {
//            progressBar.visibility = View.VISIBLE
//            getWeatherAsync()
//            web_button.setOnClickListener {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.by/pogoda/gomel?from=serp_title"))
//                startActivity(intent)
//            }
        } else {
            Toast.makeText(
                this@MainActivity,
                "No connection",
                Toast.LENGTH_LONG
            ).show()
        }
    }

     private fun isOnline(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
    }

//    private fun parseJson(responseBody: String): WeatherDetails {
//
//        val json = JSONObject(responseBody)
//
//        var tem = ""
//        var windSpeed = ""
//        var feelsLike = ""
//        var icon = ""
//
//        if (json.has("weekForecast")) {
//
//            val weekForecast = json.getJSONObject("weekForecast")
//
//            tem = weekForecast.getString("temp")
//            windSpeed = weekForecast.getString("wind_speed")
//            feelsLike = weekForecast.getString("feels_like")
//            icon = weekForecast.getString("icon")
//        }
//        val weekForecast = Fact(tem, windSpeed, feelsLike, icon)
//
//        val forecasts = mutableListOf<WeatherDetailsByDay>()
//
//        if (json.has("forecasts")) {
//
//            val forecast = json.getJSONArray("forecasts")
//
//            for (i in 0 until forecast.length()) {
//
//                val day = forecast.getJSONObject(i)
//
//                var humidity = ""
//                var dayWindSpeed = ""
//                var dayFeelsLike = ""
//                var tempMin = ""
//                var tempMax = ""
//                val date = day.getString("date")
//
//                if (day.has("parts")) {
//
//                    val parts = day.getJSONObject("parts")
//
//                    val night = parts.getJSONObject("night")
//                    tempMin = night.getString("temp_min")
//
//                    val byDay = parts.getJSONObject("day")
//                    tempMax = byDay.getString("temp_max")
//                    dayFeelsLike = byDay.getString("feels_like")
//                    dayWindSpeed = byDay.getString("wind_speed")
//                    humidity = byDay.getString("humidity")
//
//                }
//                val dayInfo = DayInfo(tempMin, tempMax, dayWindSpeed, humidity, dayFeelsLike)
//                val weatherDetailsByDay = WeatherDetailsByDay(date, dayInfo)
//                forecasts.add(weatherDetailsByDay)
//            }
//        }
//
//        SvgLoader.pluck().with(this).load("https://yastatic.net/weather/i/icons/blueye/color/svg/$icon.svg", iconDay)
//        return WeatherDetails(weekForecast, forecasts)
//    }

//    private fun updateUI(weatherDetails: WeatherDetails) {
//
//        if (weatherDetails.forecasts.isNotEmpty()) {
//            recycler.apply {
//                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
//                adapter = DaysAdapter(weatherDetails.forecasts)
//            }
//        }else{
//
//            Toast.makeText(
//                this@MainActivity,
//                "There is no forecasts for now...",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
//        SvgLoader.pluck().close()
    }

//    private fun getWeatherAsync() {
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://api.weather.yandex.ru/v1/forecast?lat=52.4345000&lon=30.9754000&lang=ru_RU&limit=1&hours=folse&extra=folse")
//            .get()
//            .addHeader("X-Yandex-API-Key", "cc7c63c1-f984-40cd-9b8f-fe155ca488b0")
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Something went wrong, exception is: $e",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//
//                val responseBody = response.body()?.string()?: ""
//
//                Handler(Looper.getMainLooper()).post {
////                    updateUI(parseJson(responseBody))
//
//                    progressBar.visibility = View.GONE
//                }
//            }
//
//        })
//    }
}
