package com.example.week78lab_soal1.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week78lab_soal1.R
import com.example.week78lab_soal1.ui.model.*

@Composable
fun WeatherSuccessView(
    weather: WeatherResponse,
    modifier: Modifier = Modifier
) {
    val weatherCondition = weather.weather.firstOrNull()?.main ?: "Clear"

    // Choose panda image based on weather
    val pandaImage = when (weatherCondition.lowercase()) {
        "clear" -> R.drawable.panda_clear
        "clouds" -> R.drawable.panda_cloud
        else -> R.drawable.panda_rain

    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Panda based on weather
        Image(
            painter = painterResource(id = pandaImage),
            contentDescription = "Panda $weatherCondition",
            modifier = Modifier.size(220.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Weather icon from API
        val iconUrl = "https://openweathermap.org/img/wn/${weather.weather.first().icon}@4x.png"
        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather icon",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // City name and country
        Text(
            text = "${weather.name}, ${weather.sys.country}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Temperature
        Text(
            text = "${weather.main.temp.toInt()}°C",
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        // Weather description
        Text(
            text = weather.weather.first().description.replaceFirstChar { it.uppercase() },
            fontSize = 22.sp,
            color = Color.White.copy(alpha = 0.95f),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Weather details card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.95f)
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Weather Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                WeatherDetailRow("Feels Like", "${weather.main.feelsLike.toInt()}°C")
                WeatherDetailRow("Min / Max", "${weather.main.tempMin.toInt()}°C / ${weather.main.tempMax.toInt()}°C")
                WeatherDetailRow("Humidity", "${weather.main.humidity}%")
                WeatherDetailRow("Wind Speed", "${weather.wind.speed} m/s")
                WeatherDetailRow("Pressure", "${weather.main.pressure} hPa")
                WeatherDetailRow("Visibility", "${weather.visibility / 1000} km")
                WeatherDetailRow("Cloudiness", "${weather.clouds.all}%")
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun WeatherDetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121)
        )
    }
}

// Preview with dummy data
@Preview(showBackground = true)
@Composable
private fun WeatherSuccessViewPreview() {
    val dummyWeather = WeatherResponse(
        coord = Coord(112.7521, -7.2575),
        weather = listOf(
            Weather(
                id = 800,
                main = "Clear",
                description = "clear sky",
                icon = "01d"
            )
        ),
        base = "stations",
        main = Main(
            temp = 32.5,
            feelsLike = 35.2,
            tempMin = 31.0,
            tempMax = 34.0,
            pressure = 1012,
            humidity = 65
        ),
        visibility = 10000,
        wind = Wind(speed = 3.5, deg = 120),
        clouds = Clouds(all = 0),
        dt = 1234567890,
        sys = Sys(
            type = 1,
            id = 9374,
            country = "ID",
            sunrise = 1234567890,
            sunset = 1234567890
        ),
        timezone = 25200,
        id = 1625822,
        name = "Surabaya",
        cod = 200
    )

    WeatherSuccessView(weather = dummyWeather)
}