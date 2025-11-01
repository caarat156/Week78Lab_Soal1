package com.example.week78lab_soal1.ui.model

import androidx.annotation.DrawableRes

data class WeatherDetailItem(
    @DrawableRes val iconRes: Int,  // Changed to drawable resource
    val label: String,
    val value: String
)

fun com.example.week78lab_soal1.data.dto.WeatherResponse.toWeatherDetailItems(): List<WeatherDetailItem> {
    return listOf(
        WeatherDetailItem(
            iconRes = com.example.week78lab_soal1.R.drawable.humidity,
            label = "HUMIDITY",
            value = "${main.humidity}%"
        ),
        WeatherDetailItem(
            iconRes = com.example.week78lab_soal1.R.drawable.wind,
            label = "WIND",
            value = "${wind.speed.toInt()}km/h"
        ),
        WeatherDetailItem(
            iconRes = com.example.week78lab_soal1.R.drawable.feels_like,
            label = "FEELS LIKE",
            value = "${main.feelsLike.toInt()}°"
        ),
        WeatherDetailItem(
            iconRes = com.example.week78lab_soal1.R.drawable.rain,
            label = "RAIN FALL",
            value = "0.0 mm"
        ),
        WeatherDetailItem(
            iconRes = com.example.week78lab_soal1.R.drawable.devices,
            label = "PRESSURE",
            value = "${main.pressure}hPa"
        ),
        WeatherDetailItem(
            iconRes = com.example.week78lab_soal1.R.drawable.cloud,
            label = "CLOUDS",
            value = "${clouds.all}%"
        )
    )
}