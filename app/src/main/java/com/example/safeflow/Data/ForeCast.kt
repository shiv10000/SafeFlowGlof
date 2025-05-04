package com.example.safeflow.Data


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.R

// Data class for forecast information
data class HourlyForecastData(
    val time: String= "",
    val weatherIconRes: Int = R.drawable.weather2,
    val condition: String="",
    val temperature: String="",
    val isSelected: Boolean = false
)
// Provider class for forecast data
object ForecastDataProvider {
    // Function to get hourly forecast data
    fun getHourlyForecastData(): List<HourlyForecastData> {
        return listOf(
            HourlyForecastData(
                time = "12 PM",
                weatherIconRes = R.drawable.weather2,
                condition = "Mild",
                temperature = "-19°",
                isSelected = false
            ),
            HourlyForecastData(
                time = "Now",
                weatherIconRes = R.drawable.weather2,
                condition = "Mild",
                temperature = "-19°",
                isSelected = true
            ),
            HourlyForecastData(
                time = "2 PM",
                weatherIconRes = R.drawable.weather2,
                condition = "Mild",
                temperature = "-18°",
                isSelected = false
            ),
            HourlyForecastData(
                time = "3 PM",
                weatherIconRes = R.drawable.weather2,
                condition = "Mild",
                temperature = "-20°",
                isSelected = false
            ),
            HourlyForecastData(
                time = "4 PM",
                weatherIconRes = R.drawable.weather2,
                condition = "Danger",
                temperature = "-19°",
                isSelected = false
            ),
            HourlyForecastData(
                time = "5 PM",
                weatherIconRes = R.drawable.weather2,
                condition = "Mild",
                temperature = "-17°",
                isSelected = false
            )
        )
    }
    }

// ... existing code ...