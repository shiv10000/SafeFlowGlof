package com.example.safeflow.Weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.collection.intIntMapOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safeflow.R
import com.example.safeflow.Screens.Signup.Background
import com.example.safeflow.navigation.NavRoutes
import com.google.android.gms.location.LocationServices

// HomeWeather.kt
@Preview
@Composable
fun HomeWeather(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    weatherViewModel: WeatherViewModel = viewModel(),
    lakes : () -> Unit = {},
    alert : () -> Unit = {},


) {


    val context = LocalContext.current
    var showPermissionDialog by remember { mutableStateOf(false) }

    // Location Permission Launcher
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted - fetch location & weather
            getLocationAndFetchWeather(context, weatherViewModel)
        } else {
            // Permission denied - use default city
            weatherViewModel.getWeatherForCity("Leh")
        }
    }

    // Check initial permission state
    LaunchedEffect(Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        showPermissionDialog = !hasPermission
        if (hasPermission) {
            getLocationAndFetchWeather(context, weatherViewModel)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
         ) {
            Spacer(modifier = Modifier.padding(top=48.dp))

            WetherInfo()
            Spacer(modifier = Modifier.padding(top=48.dp))
            Box(){
                combineCard()
                Column(
                    modifier= Modifier.fillMaxSize().padding(20.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Image(
                            modifier = Modifier.size(75.dp).clickable { lakes() },
                            painter = painterResource(R.drawable.lakes),
                            contentDescription = null
                        )
                        Image(
                            modifier = Modifier.size(75.dp).clickable { alert()  },
                            painter = painterResource(R.drawable.alert),
                            contentDescription = null
                        )

                    }


                }




            }
          }

        // Location Permission Dialog
        if (showPermissionDialog) {
            Dialog(onDismissRequest = {
                showPermissionDialog = false
                weatherViewModel.getWeatherForCity("Leh")
            }) {
                Alertpop(
                    allowacess = {
                        showPermissionDialog = false
                        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    },
                    notallowed = {
                        showPermissionDialog = false
                        weatherViewModel.getWeatherForCity("Leh")
                    }
                )
            }
        }

    }
}
