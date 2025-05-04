package com.example.safeflow.Weather

import androidx.collection.intIntMapOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.safeflow.Data.ForecastDataProvider
import com.example.safeflow.R
@Preview
@Composable
fun HourlyForecastRow() {
    // Get forecast data from provider
    val forecasts = ForecastDataProvider.getHourlyForecastData()
    
     val selectedForecastIndex = remember { mutableStateOf(forecasts.indexOfFirst { it.isSelected }) }
    
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(forecasts.withIndex().toList()) { (index, forecast) ->
            HourlyForecast(
                time = forecast.time,
                weatherIconRes = forecast.weatherIconRes,
                condition = forecast.condition,
                temperature = forecast.temperature,
                isSelected = index == selectedForecastIndex.value,
                onClick = { selectedForecastIndex.value = index }
            )
        }
    }
}
@Composable
fun HourlyForecast(
    time: String = "12 AM",
    weatherIconRes: Int = R.drawable.weather2,
    condition: String = "Mild",
    temperature: String = "-19°",
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) { 
    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.FocusedContainer)
    } else {
        colorResource(id = R.color.UnfocusedContainer)
    }
    
    val conditionColor = if (condition == "Danger") {
        colorResource(id = R.color.teal_700) // Make sure to update this to use the correct color resource
    } else {
        colorResource(id = R.color.teal_700)
    }
    
    Column(
        modifier = Modifier
            .width(60.dp)
            .height(146.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor)
            .padding(vertical = 20.dp, horizontal = 8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val sfproregular = FontFamily(
            Font(R.font.sfproregular)
        )
        Text(
            fontFamily = sfproregular,
            text = time,
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            modifier = Modifier
                .size(42.dp),
            painter = painterResource(weatherIconRes),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            fontFamily = sfproregular,
            text = condition,
            color = conditionColor,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = temperature,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
@Preview
@Composable
fun BottomCard(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.bottom),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(390.dp),
            alignment = Alignment.BottomCenter
        )
    }
}

@Preview
@Composable
fun combineCard() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomCard()

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

                HourlyForecastRow()


        }


    }
}
