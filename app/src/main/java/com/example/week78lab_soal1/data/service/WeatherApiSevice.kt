package com.example.week78lab_soal1.data.service

import com.example.week78lab_soal1.data.dto.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

import com.example.panpanweather.data.dto.ResponseWeather
import com.example.panpanweather.data.dto.ResponseForecast
import com.example.panpanweather.data.network.ApiConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = ApiConfig.UNITS_METRIC,
        @Query("lang") lang: String = ApiConfig.LANG_EN
    ): Response<WeatherResponse>

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): WeatherApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}