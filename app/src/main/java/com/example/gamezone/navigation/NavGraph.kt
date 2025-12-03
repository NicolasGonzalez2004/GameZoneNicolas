package com.example.gamezone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamezone.ui.JuegosScreen

import com.example.gamezone.ui.auth.LoginScreen
import com.example.gamezone.ui.auth.RegisterScreen
import com.example.gamezone.ui.theme.RandomUserScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"   // pantalla inicial
    ) {

        // ---------- LOGIN ----------
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("juegos") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onCreateAccount = {
                    navController.navigate("register")
                }
            )
        }

        // ---------- REGISTER ----------
        composable(route = "register") {
            RegisterScreen(
                onRegisterDone = {
                    navController.popBackStack() // vuelve a login
                }
            )
        }

        // ---------- JUEGOS ----------
        composable(route = "juegos") {
            JuegosScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("juegos") { inclusive = true }
                    }
                },
                onRandomUser = {
                    navController.navigate("randomuser")
                }
            )
        }

        // ---------- RANDOM USER (API externa) ----------
        composable(route = "randomuser") {
            // Tu pantalla que consume https://randomuser.me
            RandomUserScreen()
        }
    }
}
