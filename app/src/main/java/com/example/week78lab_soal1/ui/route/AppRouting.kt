package com.example.week78lab_soal1.ui.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week78lab_soal1.ui.view.WeatherView

enum class AppScreen(val title: String) {
    Weather("PanPan Weather")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentScreen: AppScreen,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = currentScreen.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1a1d3a).copy(alpha = 0.9f)
        )
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AppRouting() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            MyTopAppBar(currentScreen = AppScreen.Weather)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppScreen.Weather.name
        ) {
            composable(route = AppScreen.Weather.name) {
                WeatherView()
            }
        }
    }
}