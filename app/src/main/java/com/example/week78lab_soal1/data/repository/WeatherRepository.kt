package com.example.week78lab_soal1.data.repository

import com.example.week78lab_soal1.data.container.WeatherContainer
import com.example.week78lab_soal1.data.dto.WeatherResponse
import com.example.week78lab_soal1.data.service.WeatherApiService

class WeatherRepository(
    private val apiService: WeatherApiService
) {

    suspend fun getWeather(city: String): WeatherResponse {
        val response = apiService.getWeather(
            city = city,
            apiKey = WeatherContainer.API_KEY
        )

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            when (response.code()) {
                404 -> throw Exception("City not found. Please check the city name and try again.")
                401 -> throw Exception("Invalid API key")
                else -> throw Exception("Error: ${response.message()}")
            }
        }
    }
}