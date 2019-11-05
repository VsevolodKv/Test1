package com.example.web.dataclasess

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Night(
    val humidityNight: String,
    val windSpeedNight: String,
    val feelsLikeNight: String,
    val tempMinNight: String,
    val tempMaxNight: String,
    val iconViewNight: String,
    val tempAvgNight: String,
    val conditionNight: String,
    val windGustNight: String,
    val pressureMmNight: String,
    val pressurePaNight: String,
    val precMmNight: String,
    val precPeriodNight: String,
    val precProbNight: String
): Parcelable