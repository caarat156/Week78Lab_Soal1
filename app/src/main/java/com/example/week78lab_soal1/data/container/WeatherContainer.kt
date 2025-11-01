package com.example.week78lab_soal1.data.container

import com.example.week78lab_soal1.data.repository.WeatherRepository
import com.example.week78lab_soal1.data.service.WeatherApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherContainer {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val ICON_URL = "https://openweathermap.org/img/wn/"

        // GANTI DENGAN API KEY KAMU DARI OPENWEATHERMAP
        const val API_KEY = "ac4b65381c37ab3bca3d9ea5bca5e7e7"

        // Helper function untuk mendapatkan weather icon URL
        fun getWeatherIconUrl(icon: String): String {
            return "${ICON_URL}${icon}@4x.png"
        }
    }

    // OkHttp client dengan logging interceptor untuk debugging
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Retrofit instance
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    // WeatherApiService instance (lazy initialization)
    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    // WeatherRepository instance (lazy initialization)
    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(retrofitService)
    }
}