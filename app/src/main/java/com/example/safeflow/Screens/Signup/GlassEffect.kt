package com.example.safeflow.Screens.Signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.MutatePriority
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.MonotonicFrameClock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.safeflow.R

@Composable
fun GlassmorphismCard(
    onPhoneNumberChange: (String) -> Unit

) {
    var phoneNumber by remember { mutableStateOf("") }

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
                    .padding(horizontal = 20.dp)
                    .padding(top = 13.dp, bottom = 13.dp)
            ){
                val kanitFont = FontFamily(
                    Font(R.font.kanit ) // Assuming you have kanit_regular.ttf in the res/font folder
                )

                Text(
                    text = stringResource(R.string.WelcomeMessage), // Replace this with the actual string value
                    fontSize = 32.33.sp,
                    color = Color.White,
                    fontFamily = kanitFont
                )
                Text(
                    text = stringResource(R.string.desc),
                    fontSize = 16.33.sp,
                    color = colorResource(R.color.description_text_color),
                    fontFamily = kanitFont
                )
                Spacer(
                    modifier = Modifier.padding(top =40.dp)
                )
                Text(
                    text = stringResource(R.string.MobileNo),
                    fontSize = 16.33.sp,
                    color = colorResource(R.color.description_text_color),
                    fontFamily = kanitFont
                )
                Spacer(
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                     horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    RoundedTransparentBox(
                        modifier = Modifier.size(width = 74.dp, height = 50.dp)
                    ){
                        Row(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)

                        ){
                            Icon(
                                painter = painterResource(R.drawable.country),
                                contentDescription = "India Flag",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(height = 16.dp, width = 14.dp)
                            )

                            Text(
                                text = "+91",
                                fontSize = 16.33.sp,
                                color = Color.White,
                             )

                        }

                    }
                    RoundedTransparentBox(
                        modifier = Modifier.size(width = 221.dp, height = 50.dp)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize().padding(start = 10.dp),
                            verticalArrangement = Arrangement.Center,


                            ){
                            TransparentNumberInput(
                                value = phoneNumber,
                                onValueChange = {
                                    phoneNumber = it
                                    onPhoneNumberChange(it)
                                },
                                placeholder = "Enter number"
                            )
                        }


                    }

                }




            }



        }
    }



@Composable
fun RoundedTransparentBox(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(cornerRadius.dp),
                spotColor = colorResource(R.color.borderColoe).copy(alpha = 0.2f)
            )
            .clip(RoundedCornerShape(cornerRadius.dp))
            .background(Color.White.copy(alpha = 0.05f)) // 5% fill of #FFFFFF
            .border(
                width = 1.dp,
                color = colorResource(R.color.borderColoe), // Stroke with #FFFFFF
                shape = RoundedCornerShape(cornerRadius.dp)
            )
             ,
        content = content
    )
}

@Composable
fun TransparentNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter number"
) {
    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            // Filter to only allow numbers
            val filtered = newValue.filter { it.isDigit() }
            onValueChange(filtered)
        },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(
            color = Color.White,
            textAlign = TextAlign.Start // Changed from Center for better UX
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        cursorBrush = SolidColor(colorResource(R.color.focused)),
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Show placeholder when the value is empty
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                }
                innerTextField()
            }
        }
    )
}
@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpLength: Int = 6,
    onOtpChange: (String) -> Unit,
    onOtpComplete: (String) -> Unit
) {
    var currentFocusIndex by remember { mutableStateOf(0) }
    val otpList = remember { mutableStateListOf<String>().apply {
        addAll(List(otpLength) { "" })
    }}
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(otpLength) { index ->
            OtpDigitInputBox(
                value = otpList[index],
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                        otpList[index] = if (newValue.length > 1) newValue.last().toString() else newValue
                        currentFocusIndex = index

                        val currentOtp = otpList.joinToString("")
                        onOtpChange(currentOtp)

                        if (newValue.isNotEmpty() && index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }

                        if (currentOtp.length == otpLength) {
                            onOtpComplete(currentOtp)
                            keyboardController?.hide()
                        }
                    }
                },
                onBackspace = {
                    if (index > 0) {
                        otpList[index - 1] = ""
                        focusRequesters[index - 1].requestFocus()
                    }
                },
                focusRequester = focusRequesters[index]
            )
        }
    }
}

@Composable
fun OtpDigitInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    onBackspace: () -> Unit,
    focusRequester: FocusRequester
) {
    val isFocused = remember { mutableStateOf(false) }
    val isFilled = value.isNotEmpty()
    Box(
        modifier = Modifier
            .size(height = 45.dp, width = 45.dp)
            .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center // Ensure content is centered
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.isEmpty()) {
                        onValueChange("")
                        if (value.isEmpty()) {
                            onBackspace()
                        }
                    } else {
                        // Only take the last digit if multiple were pasted
                        onValueChange(if (newValue.length > 1) newValue.last().toString() else newValue)
                    }
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused.value = it.isFocused }
                    .width(40.dp) // Fixed width for better alignment
                    .height(40.dp), // Fixed height for better positioning
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 28.sp, // Larger text for better visibility
                    fontWeight = FontWeight.Bold, // Bolder text for better visibility
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                cursorBrush = SolidColor(Color.White),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
            if (!isFilled) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                        .background(
                            color = if (isFocused.value)
                                Color.White
                            else
                                Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}
@Composable
fun RoundedTransparentBox2(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(cornerRadius.dp),
                spotColor = colorResource(R.color.borderColoe).copy(alpha = 0.2f)
            )
            .clip(RoundedCornerShape(cornerRadius.dp))
            .background(Color.White.copy(alpha = 0.05f)) // 5% fill of #FFFFFF
            .border(
                width = 1.dp,
                color = colorResource(R.color.borderColoe), // Stroke with #FFFFFF
                shape = RoundedCornerShape(cornerRadius.dp)
            ),
        content = content
    )
}

