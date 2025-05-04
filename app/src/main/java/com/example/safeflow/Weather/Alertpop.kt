package com.example.safeflow.Weather

import android.service.controls.templates.TemperatureControlTemplate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safeflow.R



@Composable
fun Alertpop(modifier: Modifier =Modifier,
             allowacess : () -> Unit,
             notallowed : () -> Unit,


             ){
    ElevatedCard(
        modifier = Modifier.size(width = 270.dp, height = 190.dp),

        colors = CardDefaults.elevatedCardColors(

            containerColor = colorResource(R.color.AlertCard),
        )

    ) {
        val sfproregular = FontFamily(
            Font(R.font.sfproregular ) // Assuming you have kanit_regular.ttf in the res/font folder
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
         ){
            Spacer(Modifier.padding(top = 22.dp))
            Text(
                text = stringResource(R.string.alertpop),
                color = Color.Black,
                fontFamily = sfproregular,
                fontSize = 17.sp

            )
            Text(
                text = buildAnnotatedString {
                    val fullText = stringResource(R.string.alertpop2) // "your location and Run on"

                   val boldWords = listOf("Location", "Run","on") // Words you want to make bold

                    var lastIndex = 0

                    for (word in boldWords) {
                        val startIndex = fullText.indexOf(word, lastIndex)
                        if (startIndex >= 0) {
                           append(fullText.substring(lastIndex, startIndex))

                             withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(word)
                            }

                            lastIndex = startIndex + word.length
                        }
                    }

                     if (lastIndex < fullText.length) {
                        append(fullText.substring(lastIndex))
                    }
                },
                fontFamily = sfproregular,
                fontSize = 17.sp,
                color = Color.Black

                )
            Text(
                text = buildAnnotatedString {
                    val fullText = stringResource(R.string.alertpop3) // "your location and Run on"

                    val boldWords = listOf("Background", "Acess") // Words you want to make bold

                    var lastIndex = 0

                    for (word in boldWords) {
                        val startIndex = fullText.indexOf(word, lastIndex)
                        if (startIndex >= 0) {
                            append(fullText.substring(lastIndex, startIndex))

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(word)
                            }

                            lastIndex = startIndex + word.length
                        }
                    }

                    if (lastIndex < fullText.length) {
                        append(fullText.substring(lastIndex))
                    }
                },
                fontFamily = sfproregular,
                fontSize = 17.sp,
                color = Color.Black

            )
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.clickable{ allowacess() },
                text = "Allow Acess",
                fontFamily = sfproregular,
                color = colorResource(R.color.Access),
                fontSize = 17.sp,
             )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.clickable{ notallowed() },
                text = "Don't Allow, Reject",
                fontFamily = sfproregular,
                fontSize = 17.sp,
                color = colorResource(R.color.Access),
             )


        }
    }

}

