package com.example.week78lab_soal1.data.repository

import com.example.week78lab_soal1.data.dto.WeatherResponse
import com.example.week78lab_soal1.data.service.WeatherApiService

class WeatherRepository(
    private val apiService: WeatherApiService
) {
    private val API_KEY = "ac4b65381c37ab3bca3d9ea5bca5e7e7"

    suspend fun getWeather(city: String): Result<WeatherResponse> {
        return try {
            val response = apiService.getWeather(city, API_KEY)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                when (response.code()) {
                    404 -> Result.failure(Exception("City not found"))
                    401 -> Result.failure(Exception("Invalid API key"))
                    else -> Result.failure(Exception("Error: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }
}