package com.example.web.dataObject

import com.google.gson.annotations.SerializedName


class forecasts(
    @SerializedName("date")
    val date: String?,
    @SerializedName("date_ts")
    val dateTs: Double? = null,
    @SerializedName("week")
    val week: Double? = null,
    @SerializedName("sunrise")
    val sunrise: String? = null,
    @SerializedName("sunset")
    val sunset: String? = null,
    @SerializedName("rise_begin")
    val riseBegin: String? = null,
    @SerializedName("set_end")
    val setEnd: String? = null,
    @SerializedName("moon_code")
    val moonCode: Double? = null,
    @SerializedName("moon_text")
    val moonText: String? = null,
    @SerializedName("parts")
    val parts: Parts)