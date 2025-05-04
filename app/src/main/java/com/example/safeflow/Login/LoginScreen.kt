package com.example.safeflow.Login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.safeflow.Screens.Signup.Background
import com.example.safeflow.Screens.Signup.GradientButton
import com.example.safeflow.Screens.Signup.Logo
import com.example.safeflow.Screens.Signup.TermsandCondition

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    phoneNumber: String = "",
    onLoginClicked: (String) -> Unit = {}  // Changed to accept String
) {
    var otp by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 48.dp))
            Logo()
            Spacer(modifier = Modifier.padding(top = 48.dp))
            GlassmorphismCardLogin(
                phoneNumber = phoneNumber,
                onOtpChanged = { otp = it }
            )
            Spacer(modifier = Modifier.padding(top = 22.dp))
            GradientButton(
                text = "Verify OTP",
                onClick = { onLoginClicked(otp) }
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            TermsandCondition()
        }
    }
}