package com.example.week78lab_soal1.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week78lab_soal1.ui.model.WeatherUiState
import com.example.week78lab_soal1.data.repository.WeatherRepository
import com.example.week78lab_soal1.data.service.WeatherApiService
import com.example.week78lab_soal1.ui.viewmodel.WeatherViewModel
import com.example.week78lab_soal1.ui.viewmodel.WeatherViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherView(
    modifier: Modifier = Modifier
) {
    // Initialize ViewModel with Factory
    val apiService = remember { WeatherApiService.create() }
    val repository = remember { WeatherRepository(apiService) }
    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF87CEEB)) // Sky blue background
    ) {
        // Fixed Search Bar at top
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Enter city name") },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { viewModel.searchWeather() },
                    modifier = Modifier.height(56.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        }

        // Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                when (uiState) {
                    is WeatherUiState.Initial -> {
                        WeatherInitialView()
                    }
                    is WeatherUiState.Loading -> {
                        WeatherLoadingView()
                    }
                    is WeatherUiState.Success -> {
                        WeatherSuccessView((uiState as WeatherUiState.Success).weather)
                    }
                    is WeatherUiState.Error -> {
                        WeatherErrorView((uiState as WeatherUiState.Error).message)
                    }
                }
            }
        }
    }
}