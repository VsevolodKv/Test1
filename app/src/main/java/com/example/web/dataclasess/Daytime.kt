package com.example.web.dataclasess

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Daytime(
    val windDirDaytime: String,
    val humidityDaytime: String,
    val windSpeedDaytime: String,
    val feelsLikeDaytime: String,
    val tempMinDaytime: String,
    val tempMaxDaytime: String,
    val iconViewDaytime: String,
    val tempAvgDaytime: String,
    val conditionDaytime: String,
    val windGustDaytime: String,
    val pressureMmDaytime: String,
    val pressurePaDaytime: String,
    val precMmDaytime: String,
    val precPeriodDaytime: String
): Parcelable
