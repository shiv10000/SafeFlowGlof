package com.example.safeflow.Screens.Signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun OtpEnter(
    modifier: Modifier = Modifier,
    onSendOtpClicked: (String) -> Unit = {}
) {
    var phoneNumber by remember { mutableStateOf("") }

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
            GlassmorphismCard(
                onPhoneNumberChange = { number ->
                    phoneNumber = number
                }
            )
            Spacer(modifier = Modifier.padding(top = 22.dp))
            GradientButton(
                text = "Send Otp",
                onClick = { onSendOtpClicked(phoneNumber) }
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            TermsandCondition()
        }
    }
}