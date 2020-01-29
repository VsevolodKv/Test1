package com.example.web.dataObject

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class Night(
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_avg")
    val tempAvg: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mm")
    val pressureMm: Double,
    @SerializedName("pressure_pa")
    val pressurePa: Double,
    @SerializedName("humidity")
    val humidity: Double,
    val precMm: Double,
    @SerializedName("prec_period")
    val precPeriod: Double
) : BaseDayNight(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(tempMin)
        parcel.writeDouble(tempMax)
        parcel.writeDouble(tempAvg)
        parcel.writeDouble(feelsLike)
        parcel.writeString(icon)
        parcel.writeString(condition)
        parcel.writeDouble(windSpeed)
        parcel.writeDouble(windGust)
        parcel.writeString(windDir)
        parcel.writeDouble(pressureMm)
        parcel.writeDouble(pressurePa)
        parcel.writeDouble(humidity)
        parcel.writeDouble(precMm)
        parcel.writeDouble(precPeriod)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Night> {
        override fun createFromParcel(parcel: Parcel): Night {
            return Night(parcel)
        }

        override fun newArray(size: Int): Array<Night?> {
            return arrayOfNulls(size)
        }
    }
}