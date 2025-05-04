package com.example.safeflow.HomePage

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.delay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safeflow.Data.LakeData
import com.example.safeflow.Data.LakeDataProvider
import com.example.safeflow.R


@Composable
fun GlassmorphismLakesLazyColumn(
    modifier: Modifier = Modifier,
    lakes: List<LakeData> = LakeDataProvider.getLakeData()
) {
    var isLoading by remember { mutableStateOf(true) }

    // Simulate loading delay
    LaunchedEffect(Unit) {
        delay(5000) // 5 seconds delay
        isLoading = false
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(if (isLoading) 5 else lakes.size) { index ->
            if (isLoading) {
                ShimmerLakeCard()
            } else {
                LakeCard(lake = lakes[index])
            }
        }
    }
}
@Composable
fun ShimmerLakeCard(modifier: Modifier = Modifier) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(
        modifier = modifier
            .width(350.dp)
            .height(155.dp)
            .clip(RoundedCornerShape(22.25.dp))
            .background(Color.LightGray.copy(alpha = 0.3f))
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
            ) {
                // Shimmer for image
                Spacer(
                    modifier = Modifier
                        .width(115.dp)
                        .height(132.dp)
                        .background(brush)
                )

                Column(verticalArrangement = Arrangement.Center) {
                    // Shimmer for text elements
                    repeat(4) {
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth(when (it) {
                                    0 -> 0.7f
                                    1 -> 0.9f
                                    else -> 0.8f
                                })
                                .background(brush, RoundedCornerShape(4.dp))
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

// Update your LoadingScreen
@Composable
fun LoadingScreen( ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(5) {
            ShimmerLakeCard()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun LakeCard(
    lake: LakeData,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .width(350.dp)
            .height(155.dp)
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
                .fillMaxWidth()
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
                    painter = painterResource(lake.imageRes),
                    contentDescription = "${lake.name} Image",
                    modifier = Modifier
                        .width(115.dp)
                        .height(132.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = lake.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.height(43.dp),
                        fontFamily = FontFamily(Font(R.font.kanit))
                    )
                    Text(
                        text = lake.distance,
                        fontSize = 13.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.kanit))
                    )
                    Row {
                        Text(
                            text = "Risk Status: ",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.kanit))
                        )
                        Text(
                            text = lake.riskStatus,
                            fontSize = 13.sp,
                            color = when(lake.riskStatus) {
                                "Safe" -> Color.Green
                                "Moderate" -> Color(0xFFFFAA00) // Orange/Amber
                                "High" -> Color.Red
                                else -> Color.White
                            },
                            fontFamily = FontFamily(Font(R.font.kanit))
                        )
                    }
                    Text(
                        text = lake.type,
                        fontSize = 13.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.kanit))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GlassmorphismLakesPreview() {
    GlassmorphismLakesLazyColumn()
}




@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = null)

}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_broken_image), contentDescription = ""
        )
        Text(text = "Error", modifier = Modifier.padding(16.dp))
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}
