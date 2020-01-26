package com.example.web.dataObject

import com.google.gson.annotations.SerializedName


class Parts(
    @SerializedName("night")
    val night: Night,
    @SerializedName("day")
    val day: Day
){
}