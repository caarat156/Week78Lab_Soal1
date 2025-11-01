package com.example.week78lab_soal1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week78lab_soal1.data.model.WeatherUiState
import com.example.week78lab_soal1.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

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

            repository.getWeather(city).fold(
                onSuccess = { weather ->
                    _uiState.value = WeatherUiState.Success(weather)
                },
                onFailure = { error ->
                    _uiState.value = WeatherUiState.Error(
                        error.message ?: "Unknown error occurred"
                    )
                }
            )
        }
    }

    fun resetToInitial() {
        _uiState.value = WeatherUiState.Initial
        _searchQuery.value = ""
    }
}