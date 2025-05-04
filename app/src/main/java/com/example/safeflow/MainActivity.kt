package com.example.safeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
 import com.example.safeflow.navigation.NavGraph
import com.example.safeflow.socket.SocketManager
import com.example.safeflow.ui.theme.SafeflowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        SocketManager.init()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeflowTheme {
              val navController = rememberNavController()
                NavGraph(navController = navController)
             }
        }
    }
}

