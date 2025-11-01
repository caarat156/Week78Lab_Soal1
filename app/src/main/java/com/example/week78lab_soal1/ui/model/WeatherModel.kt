package com.example.week78lab_soal1.ui.model

import com.example.week78lab_soal1.data.dto.WeatherResponse

sealed class WeatherUiState {
    object Initial : WeatherUiState()
    data class Success(val weather: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

// Helper untuk mapping weather condition ke panda image
enum class WeatherCondition {
    CLEAR,
    CLOUDS,
    RAIN,
    SNOW,
    THUNDERSTORM,
    DRIZZLE,
    MIST,
    UNKNOWN;

    companion object {
        fun fromWeatherMain(main: String): WeatherCondition {
            return when (main.lowercase()) {
                "clear" -> CLEAR
                "clouds" -> CLOUDS
                "rain" -> RAIN
                "snow" -> SNOW
                "thunderstorm" -> THUNDERSTORM
                "drizzle" -> DRIZZLE
                "mist", "smoke", "haze", "dust", "fog", "sand", "ash", "squall", "tornado" -> MIST
                else -> UNKNOWN
            }
        }
    }
}