package com.example.safeflow.Weather

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import android.Manifest
import androidx.annotation.RequiresPermission

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safeflow.R
import com.google.android.gms.location.LocationServices

@Preview
@Composable
fun WetherInfo(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val sfproregular = FontFamily(
            Font(R.font.sfproregular)
        )
        Text(
            text = weatherState.location,
            fontSize = 34.sp,
            color = Color.White,
            fontFamily = sfproregular
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = weatherState.temperature.ifEmpty { "--°" },
            fontSize = 70.sp,
            color = Color.White,
            fontFamily = sfproregular
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text ="Risk status : Safe",
            fontSize = 20.sp,
            color = colorResource(R.color.description_text_color),
            fontFamily = sfproregular,
        )
    }

}

   @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
   fun getLocationAndFetchWeather(context: Context, viewModel: WeatherViewModel) {
    try {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    // Use coordinates to fetch weather
                    viewModel.getWeatherByCoordinates(location.latitude, location.longitude)
                } else {
                    // Fallback if location is null
                    viewModel.getWeatherForCity("Ghaziabad")
                }
            }.addOnFailureListener {
                // Error getting location
                viewModel.getWeatherForCity("Ghaziabad")
            }
        } else {
            // Permission not granted
            viewModel.getWeatherForCity("Ghaziabad")
        }
    } catch (e: Exception) {
        // Exception handling
        viewModel.getWeatherForCity("Ghaziabad")
    }}