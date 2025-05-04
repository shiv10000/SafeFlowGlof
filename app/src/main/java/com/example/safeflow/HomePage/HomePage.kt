package com.example.safeflow.HomePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safeflow.Data.LakeDataProvider
import com.example.safeflow.Data.MarsPhoto
import com.example.safeflow.Login.GlassmorphismCardLogin
import com.example.safeflow.Screens.Signup.Background
import com.example.safeflow.Screens.Signup.GradientButton
import com.example.safeflow.Screens.Signup.Logo
import com.example.safeflow.Screens.Signup.TermsandCondition
@Preview
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    onWeatherClicked: () -> Unit = {},
    onBackButtonClicked : () -> Unit = {}
){

    Box(modifier = Modifier.fillMaxSize()){
        Background()
        Column(
            modifier= Modifier.fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalAlignment =   Alignment.CenterHorizontally
        ){
            Spacer(modifier= Modifier.padding(top= 48.dp))
            HomeHead(backButton = onBackButtonClicked)
            GlassmorphismLakesLazyColumn(
                lakes = LakeDataProvider.getLakeData()
            )
            Spacer(modifier= Modifier.padding(top= 16.dp))
        }
    }
}




