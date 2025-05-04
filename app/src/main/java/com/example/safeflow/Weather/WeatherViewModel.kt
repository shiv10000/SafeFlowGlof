package com.example.safeflow.Weather


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import com.example.safeflow.Data.HourlyForecastData
import com.google.android.gms.location.LocationServices
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.flow.MutableStateFlow
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

// Data class for weather information
data class WeatherInfoData(
    val location: String = "",
    val temperature: String = "",
    val description: String = "",
    val humidity: String = "",
    val windSpeed: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)

interface WeatherApi {
    // Existing city endpoint
    @GET("data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "3b503b96d085c589291a3a5a9e508da8"
    ): WeatherResponse

    // New coordinate endpoint
    @GET("data/2.5/weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "3b503b96d085c589291a3a5a9e508da8"
    ): WeatherResponse
}

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "name") val name: String,
    @Json(name = "main") val main: MainWeather,
    @Json(name = "weather") val weather: List<WeatherDescription>,
    @Json(name = "wind") val wind: Wind
)

@JsonClass(generateAdapter = true)
data class MainWeather(
    @Json(name = "temp") val temp: Double,
    @Json(name = "humidity") val humidity: Int
)

@JsonClass(generateAdapter = true)
data class WeatherDescription(
    @Json(name = "description") val description: String
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "speed") val speed: Double
)

class WeatherViewModel : ViewModel() {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // For hourly forecast
    private val _uiState = MutableStateFlow(HourlyForecastData())
    val uiState: StateFlow<HourlyForecastData> = _uiState.asStateFlow()

    // For current weather
    private val _weatherState = MutableStateFlow(WeatherInfoData(isLoading = false))
    val weatherState: StateFlow<WeatherInfoData> = _weatherState.asStateFlow()

    // API initialization
    private val api: WeatherApi by lazy {
        val logging = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherApi::class.java)
    }

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        _uiState.value = HourlyForecastData()
        // Default city loading is now handled by permission flow
    }

    // Function to fetch weather by coordinates
    fun getWeatherByCoordinates(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _weatherState.update { it.copy(isLoading = true, error = "") }
                val response = api.getWeatherByCoordinates(lat, lon)
                _weatherState.update {
                    it.copy(
                        location = response.name,
                        temperature = "${response.main.temp}°C",
                        description = response.weather.firstOrNull()?.description?.capitalize() ?: "",
                        humidity = "${response.main.humidity}%",
                        windSpeed = "${response.wind.speed} m/s",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to load weather: ${e.message}"
                    )
                }
            }
        }
    }

    // Function to update weather based on city
    fun getWeatherForCity(cityName: String) {
        viewModelScope.launch {
            try {
                // Show loading state
                _weatherState.update { it.copy(isLoading = true, error = "") }

                // Make API call
                val response = api.getWeatherByCity(cityName)

                // Update state with response
                _weatherState.update {
                    it.copy(
                        location = response.name,
                        temperature = "${response.main.temp}°C",
                        description = response.weather.firstOrNull()?.description?.capitalize() ?: "",
                        humidity = "${response.main.humidity}%",
                        windSpeed = "${response.wind.speed} m/s",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                // Handle error
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to load weather: ${e.message}"
                    )
                }
            }
        }
    }

    // Helper extension function
    private fun String.capitalize(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}