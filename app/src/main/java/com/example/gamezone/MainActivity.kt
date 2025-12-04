package com.example.gamezone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.gamezone.navigation.AppNavGraph
import com.example.gamezone.ui.theme.GameZoneTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameZoneTheme {
                //   aplica el fondo y ocupar toda la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // controlador de navegación
                    val navController = rememberNavController()

                    // navgraph que define todas las pantallas de la app
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}
