package com.example.safeflow.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.R
import com.example.safeflow.Screens.Signup.OtpInputField
import com.example.safeflow.Screens.Signup.RoundedTransparentBox
import com.example.safeflow.Screens.Signup.TransparentNumberInput


@Composable
fun GlassmorphismCardLogin(
    phoneNumber: String,
    onOtpChanged:   (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .width(336.dp)
            .clip(RoundedCornerShape(22.25.dp))
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.4f),
                        Color.White.copy(alpha = 0f)
                    ),
                    center = Offset.Zero,
                    radius = 1000f
                ),
                shape = RoundedCornerShape(16.dp),
                alpha = 0.7f
            )
            .border(
                width = 0.89.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.4f),
                        Color(0xFFEEEDED).copy(alpha = 0.2f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 500f),
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
                alpha = 0.99f
            }
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        color = Color.White.copy(alpha = 0.1f),
                        blendMode = BlendMode.SrcOver
                    )
                }
            }
            .drawWithCache {
                val frameworkPaint = Paint().asFrameworkPaint()
                val innerShadowColor = Color.White.copy(alpha = 0.05f).toArgb()

                frameworkPaint.apply {
                    color = Color.Transparent.toArgb()
                    setShadowLayer(111.25f, -2.22f, -2.22f, innerShadowColor)
                }

                onDrawWithContent {
                    drawContent()
                    drawIntoCanvas { canvas ->
                        canvas.nativeCanvas.drawRoundRect(
                            0f, 0f,
                            size.width, size.height,
                            16.dp.toPx(), 16.dp.toPx(),
                            frameworkPaint
                        )
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 17.dp, bottom = 17.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.Otp),
                fontSize = 32.33.sp,
                color = Color.White,
             )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.OtpDesc1),
                    fontSize = 16.sp,
                    color = colorResource(R.color.description_text_color),
                 )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = stringResource(R.string.Otpdesc2),
                        fontSize = 16.sp,
                        color = colorResource(R.color.description_text_color),
                     )
                    Text(
                        text = phoneNumber,
                        fontSize = 16.sp,
                        color = colorResource(R.color.description_text_color),
                     )
                }
                Spacer(modifier = Modifier.padding(top = 21.dp))
                OtpInputField(
                    otpLength = 6,
                    onOtpComplete = onOtpChanged,
                    onOtpChange = onOtpChanged
                )
            }
        }
    }
}