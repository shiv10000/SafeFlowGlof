package com.example.safeflow.HomePage

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Preview
@Composable
fun GlassmorphismLakes() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .width(350.dp)
            .clip(RoundedCornerShape(22.25.dp))
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.4f),  // 40% white at center
                        Color.White.copy(alpha = 0f)     // Fully transparent at edges
                    ),
                    center = Offset.Zero,
                    radius = 1000f
                ),
                shape = RoundedCornerShape(16.dp),
                alpha = 0.7f // Overall transparency
            )
            .border(
                width = 0.89.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.4f),    // #FFFFFF with 40% opacity
                        Color(0xFFEEEDED).copy(alpha = 0.2f)  // #EEEDED with 20% opacity
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 500f),
                ),
                shape = RoundedCornerShape(16.dp)
            )
            // Create the blur effect for backdrop-filter
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
                alpha = 0.99f // Need this for blur to work
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
            // Add the inner box shadow
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
                .padding(horizontal = 15.dp)
                .padding(top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(R.drawable.lake1),
                    contentDescription = "Lake Image",
                    modifier = Modifier
                        .width(115.dp)
                        .height(132.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Center,

                ){
                    Text(
                        text = "Lake Chandrajal",
                        fontSize = 28.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.kanit))
                    )
                    Text(
                        text = "15km west",
                        fontSize = 13.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.kanit))
                    )
                    Row {
                        Text(
                            text = "Risk Status :",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.kanit))
                        )
                        Text(
                            text = "Safe",
                            fontSize = 13.sp,
                            color = Color.Green,
                            fontFamily = FontFamily(Font(R.font.kanit))
                        )


                    }
                    Text(
                        text = "Glacial Lake",
                        fontSize = 13.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.kanit))
                    )

                }


            }


        }
    }
}

