package com.example.safeflow.Screens.Signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.R

@Composable
fun Logo(modifier: Modifier=Modifier){
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val sfproregular = FontFamily(
            Font(R.font.sfproregular ) // Assuming you have kanit_regular.ttf in the res/font folder
        )
        Text(
            text = stringResource(R.string.AppLogo),
            fontSize = 70.sp,
            color = Color.White,
            fontFamily = sfproregular
        )
        Spacer(modifier=Modifier.padding(top= 16.dp))
        Text(
            text = stringResource(R.string.AppLogoDesc),
            fontSize = 17.sp,
            color = colorResource(R.color.Appdesc),
            fontFamily = sfproregular
        )

    }

}