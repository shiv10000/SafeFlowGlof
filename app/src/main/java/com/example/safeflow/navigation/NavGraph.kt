package com.example.safeflow.navigation

import AuthViewModel
import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.safeflow.EmergencySOSModeScreen
import com.example.safeflow.HomePage.HomePage
import com.example.safeflow.Login.LoginPage
import com.example.safeflow.Screens.Signup.OtpEnter
import com.example.safeflow.Weather.HomeWeather
import com.example.safeflow.socket.SocketManager
import com.google.firebase.auth.FirebaseAuth

// Define navigation routes
object NavRoutes {
    const val OTP_ENTER = "otp_enter"
    const val LOGIN_PAGE = "login_page"
    const val HOME_PAGE = "home_page"
    const val WEATHER_SCREEN = "weather_screen"
    const val SOS_SCREEN = "sos_screen"
}

@Composable
fun NavGraph(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val startDestination = remember {
        if (auth.currentUser != null) NavRoutes.WEATHER_SCREEN else NavRoutes.OTP_ENTER
    }
    val authViewModel: AuthViewModel = viewModel()
    var phoneNumber by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as Activity
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    // Socket.io lifecycle management
    DisposableEffect(Unit) {
        // Initialize socket if not already initialized
        if (!SocketManager.isSocketInitialized()) {
            SocketManager.init()
        }

         val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> SocketManager.connect()
                Lifecycle.Event.ON_STOP -> SocketManager.disconnect()
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        // Clean up on dispose
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Monitor for socket alerts
    LaunchedEffect(SocketManager.hasNewAlert.value) { // Key changed to value property
        if (SocketManager.hasNewAlert.value) {
            // Ensure we're not already on the SOS screen
            if (navController.currentDestination?.route != NavRoutes.SOS_SCREEN) {
                navController.navigate(NavRoutes.SOS_SCREEN) {
                    // Clear back stack to prevent going back to alert state
                    popUpTo(NavRoutes.HOME_PAGE) { inclusive = true }
                }
            }
            // Reset alert state after navigation
            SocketManager.resetAlert()
        }
    }

    // Handle auth state changes
    LaunchedEffect(authViewModel.authState) {
        when (val state = authViewModel.authState) {
            is AuthState.CodeSent -> {
                navController.navigate(NavRoutes.LOGIN_PAGE)
            }
            is AuthState.Success -> {
                navController.navigate(NavRoutes.WEATHER_SCREEN) {
                    popUpTo(NavRoutes.OTP_ENTER) { inclusive = true }
                }
            }
            is AuthState.Error -> {
                showError = true
            }
            else -> {}
        }
    }

    if (showError) {
        val errorMessage = (authViewModel.authState as? AuthState.Error)?.message ?: ""
        ErrorDialog(
            message = errorMessage,
            onDismiss = {
                showError = false
                authViewModel.resetState()
            }
        )
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavRoutes.OTP_ENTER) {
            OtpEnter(
                onSendOtpClicked = { number ->
                    phoneNumber = number
                    authViewModel.sendOtp("+91$number", activity)
                }
            )
        }

        composable(NavRoutes.LOGIN_PAGE) {
            LoginPage(
                phoneNumber = phoneNumber,
                onLoginClicked = { otp ->
                    authViewModel.verifyOtp(otp.toString())
                }
            )
        }

        composable(NavRoutes.WEATHER_SCREEN) {
            HomeWeather(
                onBackClicked = { },
                lakes = { navController.navigate(NavRoutes.HOME_PAGE) },
            )
        }

        composable(NavRoutes.SOS_SCREEN) {
            EmergencySOSModeScreen(
                // Pass the alert message if needed
                alertMessage = SocketManager.alertMessage.value
            )
        }

        composable(NavRoutes.HOME_PAGE) {
            HomePage(
                onBackButtonClicked = { navController.navigate(NavRoutes.WEATHER_SCREEN) }
            )
        }
    }
}

@Composable
fun ErrorDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error") },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}