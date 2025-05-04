package com.example.safeflow.Screens.Signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.R

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Define gradient colors for the fill
    val gradientColors = listOf(
        Color(0xFF8F66E1),
        Color(0xFF4E387B)
    )

    // Define gradient colors for the stroke
    val strokeGradient = listOf(
        Color.White,
        Color(0xFFC427FB),
        Color.White
    )

    Box(
        modifier = modifier
            .width(327.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(strokeGradient),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                Brush.horizontalGradient(gradientColors)
            )
            // Add these two modifiers to make the button clickable
            .clickable(onClick = onClick) ,
        contentAlignment = Alignment.Center
    ) {
        val kanitFont = FontFamily(
            Font(R.font.kanit)
        )

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = kanitFont,
            fontWeight = FontWeight.Medium
        )
    }
}