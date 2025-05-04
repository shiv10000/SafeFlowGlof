package com.example.safeflow
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log.e
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.Screens.Signup.Background
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.random.Random

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import java.io.IOException
import androidx.core.net.toUri
import com.example.safeflow.socket.SocketManager


@SuppressLint("RememberReturnType")
@Preview
 @Composable
fun EmergencySOSModeScreen(
    alertMessage: String = "Emergency alert activated",
    onAcknowledgeAlert: () -> Unit = {}
) {


    DisposableEffect(Unit) {
        onDispose {
            // Reset alert state when leaving the screen
            SocketManager.resetAlert()
        }
    }
    val mContext = LocalContext.current
    val phoneNumber = "01267282928"
    var timeToReach by remember { mutableStateOf("02:16:49") }
    var waveSpeed by remember { mutableStateOf(12.4) }
    var distance by remember { mutableStateOf(54.23) }
    var avgSpeed by remember { mutableStateOf(10.8)}
    var isAlarmActive by remember { mutableStateOf(true) }
    val mp = remember { MediaPlayer.create(mContext, R.raw.alert) }
    DisposableEffect(mp) {
        if (isAlarmActive) {
            mp.isLooping = true
            mp.start()
        }
        onDispose {
            mp.release()
        }
    }
    LaunchedEffect(isAlarmActive) {
        if (isAlarmActive) {
            if (!mp.isPlaying) {
                mp.start()
            }
        } else {
            if (mp.isPlaying) {
                mp.pause() // Use pause instead of stop to allow resuming
            }
        }
    }


    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Update every second

            // Generate new random values
            val newHours = 3
            val newMinutes = (0..59).random()
            val newSeconds = (0..59).random()
            timeToReach = String.format("%02d:%02d:%02d", newHours, newMinutes, newSeconds)
         }
    }

        Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val sfproregular = FontFamily(
            Font(R.font.sfproregular ) // Assuming you have kanit_regular.ttf in the res/font folder
        )
        Background()
        Column(
            modifier = Modifier
                .padding(top=50.dp)
                .padding(horizontal = 25.dp)
                 ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back arrow",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Emergency SOS Mode",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            // Subtitle
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Track the GLOF Flow",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFA3A3A3),
                    lineHeight = 18.sp
                )
                Text(
                    text = "Lake Name : Lake Tarakoma",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFA3A3A3),
                    lineHeight = 18.sp
                )
            }

            // Map Box with double border
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))

                    .padding(4.dp)

                    .clip(RoundedCornerShape(30.dp))
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "Map showing blue flow line and black contour lines with red boundary lines, rounded corners",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(30.dp))
                )
            }

             Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Time & Speed
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = timeToReach,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                lineHeight = 36.sp
                            )
                            Text(
                                fontFamily = sfproregular,
                                text = "TIME TO REACH (hh:mm:ss)",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF9CA3AF),
                                letterSpacing = 1.5.sp
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = waveSpeed.toString(),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                            Text(
                                text = "SPEED OF WAVE (Km/h)",
                                fontFamily = sfproregular,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF9CA3AF),
                                letterSpacing = 1.5.sp,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }



                    Spacer(modifier = Modifier.height(12.dp))

                    // Distance, Altitude, Avg Speed
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = distance.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            text = avgSpeed.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "DISTANCE (Km)",
                            fontFamily = sfproregular,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF9CA3AF),
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = "AVG. SPEED (Km/h)",
                            fontFamily = sfproregular,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF9CA3AF),
                            letterSpacing = 1.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                try {
                                    val browserIntent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse("https://ndma.gov.in/Natural-Hazards/Floods/Do-Donts")
                                    }
                                    mContext.startActivity(browserIntent)
                                } catch (e: Exception) {
                                    e("EmergencySOSMode", "Error opening browser: ${e.message}", e)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7F7F7F)),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(

                                text = "Precautions",
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                        Button(
                            onClick = {
                                isAlarmActive = false
                                try {
                                    val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:$phoneNumber")
                                    }
                                    mContext.startActivity(dialIntent)
                                } catch (e: Exception) {
                                    e("EmergencySOSMode", "Error launching dialer: ${e.message}", e)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF3B2D)),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp)
                        ) {

                            Text(
                                text = "Emergency",
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}



