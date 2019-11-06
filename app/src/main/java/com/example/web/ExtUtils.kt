package com.example.web
const val NO_DATA = "No data"

fun getRusConditions(input: String): String {
    return when(input) {
        "clear" -> "Ясно"
        "partly-cloudy" -> "Малооблачно"
        "cloudy" -> "Облачно"
        "overcast" -> "Пасмурно"
        "partly-cloudy-and-light-rain" -> "Небольшой дождь"
        "partly-cloudy-and-rain" -> "Дождь"
        "overcast-and-rain" -> "Сильный дождь"
        "overcast-thunderstorms-with-rain" -> "Сильный дождь, гроза"
        "cloudy-and-light-rain" -> "Небольшой дождь"
        "overcast-and-light-rain" -> "Небольшой дождь"
        "cloudy-and-rain" -> "Дождь"
        "overcast-and-wet-snow" -> "Дождь со снегом"
        "partly-cloudy-and-light-snow" -> "Небольшой снег"
        "partly-cloudy-and-snow" -> "Снег"
        "overcast-and-snow" -> "Снегопад"
        "cloudy-and-light-snow" -> "Небольшой снег"
        "overcast-and-light-snow" -> "Небольшой снег"
        "cloudy-and-snow " -> "Снег"
        else -> NO_DATA
    }
}

fun getRusWinDir(input: String): String {
    return when(input) {
        "nw" -> "северо-западное"
        "n" -> "серевное"
        "ne" -> "северо-восточное"
        "e" -> "восточное"
        "se" -> "юго-восточное"
        "s" -> "южное"
        "sw" -> "юго-западное"
        "w" -> "западное"
        "c" -> "штиль"
        else -> NO_DATA
    }
}