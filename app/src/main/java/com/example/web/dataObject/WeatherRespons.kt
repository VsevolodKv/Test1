package com.example.web.dataObject

import com.google.gson.annotations.SerializedName


class WeatherRespons(
    @SerializedName("fact")
    val fact: Fact? = null,
    @SerializedName("forecasts")
    val forecasts: List<forecasts>
)