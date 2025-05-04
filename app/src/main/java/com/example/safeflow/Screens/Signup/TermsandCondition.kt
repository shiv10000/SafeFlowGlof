package com.example.safeflow.Screens.Signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.R

@Preview
@Composable
fun TermsandCondition(

    modifier: Modifier= Modifier
){
    Column(
        modifier= Modifier.fillMaxWidth().padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        val kanitFont = FontFamily(
            Font(R.font.kanit ) // Assuming you have kanit_regular.ttf in the res/font folder
        )

        Text(
            text = stringResource(R.string.Terms_and_Condition),
            fontSize = 14.sp,
            color = colorResource(R.color.Terms1),
            fontFamily = kanitFont
        )
        Text(
            text = stringResource(R.string.Terms_and_Condition2),
            fontSize = 14.sp,
            color = colorResource(R.color.Terms2),
            fontFamily = kanitFont
        )

    }


}