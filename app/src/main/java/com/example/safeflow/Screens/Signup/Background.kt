package com.example.safeflow.Screens.Signup

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.safeflow.R
@Composable
fun Background(
    modifier: Modifier= Modifier
){
    Box(
        modifier= Modifier.fillMaxSize(),
    ){
        Image(
            painter = painterResource(R.drawable.weather),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Background Image"
        )
    }
}
