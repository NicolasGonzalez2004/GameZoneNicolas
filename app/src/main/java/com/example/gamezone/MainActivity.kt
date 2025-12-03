package com.example.gamezone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamezone.ui.JuegosScreen
import com.example.gamezone.ui.auth.LoginScreen
import com.example.gamezone.ui.auth.RegisterScreen
import com.example.gamezone.ui.theme.GameZoneTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameZoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"   //  arranca en login
                    ) {

                        //  login
                        composable(route = "login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    // si el login esta correcto avanzamos a juegos
                                    navController.navigate("juegos") {
                                        // sacamos login del backstack
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onCreateAccount = {
                                    // iremos a pantalla de registro
                                    navController.navigate("register")
                                }
                            )
                        }

                        //  register
                        composable(route = "register") {
                            RegisterScreen(
                                onRegisterDone = {
                                    // después de registrarse, volvemos al login
                                    navController.popBackStack() // vuelve a "login"
                                }
                            )
                        }

                        //  juegos
                        composable(route = "juegos") {
                            JuegosScreen(
                                onLogout = {
                                    // cerrar sesión → volver a login
                                    navController.navigate("login") {
                                        popUpTo("juegos") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
