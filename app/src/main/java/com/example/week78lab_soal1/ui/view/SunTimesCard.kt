package com.example.week78lab_soal1.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SunTimesCard(
    sunriseTimestamp: Long,
    sunsetTimestamp: Long,
    timezoneOffset: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2C3E50).copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Sunrise
            SunTimeItem(
                iconRes = com.example.week78lab_soal1.R.drawable.sunrise, // Ganti dengan drawable kamu
                label = "SUNRISE",
                time = formatUnixTime(sunriseTimestamp, timezoneOffset),
                modifier = Modifier.weight(1f)
            )

            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(80.dp)
                    .background(Color.White.copy(alpha = 0.3f))
            )

            // Sunset
            SunTimeItem(
                iconRes = com.example.week78lab_soal1.R.drawable.sunset, // Ganti dengan drawable kamu
                label = "SUNSET",
                time = formatUnixTime(sunsetTimestamp, timezoneOffset),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SunTimeItem(
    iconRes: Int,
    label: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Icon (PNG Image)
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Fit
        )

        // Label
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(alpha = 0.7f),
            letterSpacing = 1.sp
        )

        // Time
        Text(
            text = time,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

// Helper function to format Unix timestamp to readable time
fun formatUnixTime(timestamp: Long, timezoneOffset: Int): String {
    val date = Date((timestamp + timezoneOffset) * 1000L)
    val format = SimpleDateFormat("h:mm a", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    return format.format(date)
}

@Preview(showBackground = true)
@Composable
private fun SunTimesCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF87CEEB))
            .padding(16.dp)
    ) {
        SunTimesCard(
            sunriseTimestamp = 1640829720, // Example: 5:22 AM
            sunsetTimestamp = 1640862540,  // Example: 5:29 PM
            timezoneOffset = 25200 // UTC+7 (Indonesia)
        )
    }
}