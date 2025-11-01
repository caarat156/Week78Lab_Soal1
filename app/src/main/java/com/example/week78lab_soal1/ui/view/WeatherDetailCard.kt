package com.example.week78lab_soal1.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherDetailCard(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(width = 110.dp, height = 130.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4A5568).copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Icon
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(32.dp)
            )

            // Label
            Text(
                text = label,
                fontSize = 11.sp,
                color = Color.White.copy(alpha = 0.6f),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                letterSpacing = 0.5.sp
            )

            // Value
            Text(
                text = value,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Grid layout untuk semua weather detail cards
 */
@Composable
fun WeatherDetailsGrid(
    details: List<WeatherDetailItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Row 1: Humidity, Wind, Feels Like
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            details.take(3).forEach { detail ->
                WeatherDetailCard(
                    icon = detail.icon,
                    label = detail.label,
                    value = detail.value,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Row 2: Rain Fall, Pressure, Clouds
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            details.drop(3).take(3).forEach { detail ->
                WeatherDetailCard(
                    icon = detail.icon,
                    label = detail.label,
                    value = detail.value,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
private fun WeatherDetailCardPreview() {
    Box(
        modifier = Modifier
            .background(Color(0xFF2d3561))
            .padding(16.dp)
    ) {
        WeatherDetailCard(
            icon = Icons.Default.WaterDrop,
            label = "HUMIDITY",
            value = "49%"
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
private fun WeatherDetailsGridPreview() {
    Box(
        modifier = Modifier
            .background(Color(0xFF2d3561))
            .padding(16.dp)
    ) {
        val sampleDetails = listOf(
            WeatherDetailItem(Icons.Default.WaterDrop, "HUMIDITY", "49%"),
            WeatherDetailItem(Icons.Default.Air, "WIND", "2km/h"),
            WeatherDetailItem(Icons.Default.Thermostat, "FEELS LIKE", "32°"),
            WeatherDetailItem(Icons.Default.Umbrella, "RAIN FALL", "0.0 mm"),
            WeatherDetailItem(Icons.Default.Compress, "PRESSURE", "1011hPa"),
            WeatherDetailItem(Icons.Default.Cloud, "CLOUDS", "8%")
        )
        WeatherDetailsGrid(details = sampleDetails)
    }
}

// Import untuk WeatherDetailItem
data class WeatherDetailItem(
    val icon: ImageVector,
    val label: String,
    val value: String
)