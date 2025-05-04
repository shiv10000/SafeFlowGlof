package com.example.safeflow.Weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safeflow.R
import com.example.safeflow.Weather.WeatherViewModel



@Preview
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
) {

    // City search field state
    var cityInput by remember { mutableStateOf("Delhi") }

    // Collect weather state from ViewModel
    val weatherState by viewModel.weatherState.collectAsState()

    // Fetch Delhi weather on first launch
    LaunchedEffect(key1 = true) {
        viewModel.getWeatherForCity("Delhi")
    }
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // City search field
        OutlinedTextField(
            value = cityInput,
            onValueChange = { cityInput = it },
            label = { Text("Enter City") },
            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.getWeatherForCity(cityInput)
                }
            )
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        // Weather info display
        WeatherInfo(
            cityName = weatherState.location.ifEmpty { "Delhi" },
            temperature = weatherState.temperature.ifEmpty { "--°" },
            description = weatherState.description.ifEmpty { "Loading weather data..." },
            isLoading = weatherState.isLoading,
            error = weatherState.error
        )
    }
}
@Composable
fun WeatherInfo(
    cityName: String,
    temperature: String,
    description: String,
    isLoading: Boolean,
    error: String,
    modifier: Modifier = Modifier
) {
    val sfproregular = FontFamily(
        Font(R.font.sfproregular)
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Show city name
        Text(
            text = cityName,
            fontSize = 34.sp,
            color = Color.White,
            fontFamily = sfproregular
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        // Show loading indicator or temperature
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(
                text = temperature,
                fontSize = 70.sp,
                color = Color.White,
                fontFamily = sfproregular
            )
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))

        // Show error or description
        Text(
            text = if (error.isNotEmpty()) error else description,
            fontSize = 20.sp,
            color = if (error.isNotEmpty())
                Color.Red
            else
                colorResource(R.color.description_text_color),
            fontFamily = sfproregular,
        )
    }
}