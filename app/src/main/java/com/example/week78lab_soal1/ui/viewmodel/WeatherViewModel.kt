package com.example.week78lab_soal1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week78lab_soal1.data.container.WeatherContainer
import com.example.week78lab_soal1.ui.model.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherContainer().weatherRepository

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val uiState: StateFlow<WeatherUiState> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchWeather() {
        val city = _searchQuery.value.trim()

        if (city.isEmpty()) {
            _uiState.value = WeatherUiState.Error("Please enter a city name")
            return
        }

        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading

            try {
                val weather = repository.getWeather(city)
                _uiState.value = WeatherUiState.Success(weather)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun resetToInitial() {
        _uiState.value = WeatherUiState.Initial
        _searchQuery.value = ""
    }

    // Helper function untuk weather icon URL
    fun getWeatherIconUrl(icon: String): String {
        return WeatherContainer.getWeatherIconUrl(icon)
    }
}