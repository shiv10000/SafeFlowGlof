package com.example.safeflow.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.Data.LakeDataProvider
import com.example.safeflow.R

@Composable
fun HomeHead(modifier: Modifier = Modifier,
             backButton : () -> Unit
             ){
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
         ) {
            val sfproregular = FontFamily(
                Font(R.font.sfproregular ) // Assuming you have kanit_regular.ttf in the res/font folder
            )

            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier=Modifier.size(32.dp)
                    .clickable {
                        backButton()

                    }

            )
            Spacer(modifier = Modifier.padding(start = 40.dp))
            Text(
                text = stringResource(R.string.Nearby),
                fontSize = 34.sp,
                color = Color.White,
                fontFamily = sfproregular
            )


        }

        Spacer(modifier= Modifier.padding(top= 16.dp))


    }

}