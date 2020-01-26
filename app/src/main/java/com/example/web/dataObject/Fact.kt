package com.example.web.dataObject

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Fact(@SerializedName("temp")
           val temp: Double,
           @SerializedName("feels_like")
           val feelsLike: Double,
           @SerializedName("icon")
           val icon: String,
           @SerializedName("condition")
           val condition: String,
           @SerializedName("wind_speed")
           val windSpeed: Double,
           @SerializedName("wind_gust")
           private val windGust: Double? = null,
           @SerializedName("wind_dir")
           private val windDir: String? = null,
           @SerializedName("pressure_mm")
           private val pressureMm: Double? = null,
           @SerializedName("pressure_pa")
           private val pressurePa: Double? = null,
           @SerializedName("humidity")
           val humidity: Double,
           @SerializedName("uv_index")
           private val uvIndex: Double? = null,
           @SerializedName("soil_temp")
           private val soilTemp: Double? = null,
           @SerializedName("soil_moisture")
           private val soilMoisture: Double? = null,
           @SerializedName("daytime")
           private val daytime: String? = null,
           @SerializedName("polar")
           private val polar: Boolean? = null,
           @SerializedName("season")
           private val season: String? = null,
           @SerializedName("obs_time")
           private val obsTime: Double? = null,
           @SerializedName("source")
           private val source: String? = null) {

}