package com.example.safeflow.Data

data class WeatherInfoData(
    val location: String ="",
    val temperature: String="",
    val description: String="",
    val isLoading: Boolean = false,
    val error: String? = null
)
