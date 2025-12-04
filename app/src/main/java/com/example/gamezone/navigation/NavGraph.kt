//sistema que organiza las pantallas y cómo se conectan entre sí.
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
// función que crea el grafo de navegación

    NavHost(
        navController = navController,
        startDestination = "login"   // pantalla que aparece al iniciar
    ) {

        //  login
        composable(route = "login") {
            // pantalla login
            LoginScreen(
                onLoginSuccess = {
                    // cuando logras iniciar sesión
                    navController.navigate("juegos") {
                        popUpTo("login") { inclusive = true }
                        // borra login del historial para no volver atrás
                    }
                },
                onCreateAccount = {
                    // cuando aprietas "crear cuenta"
                    navController.navigate("register")
                }
            )
        }

        //  register
        composable(route = "register") {
            // pantalla de registro
            RegisterScreen(
                onRegisterDone = {
                    navController.popBackStack()
                    // vuelve a login después de registrarte
                }
            )
        }

        // ---------- juegos ----------
        composable(route = "juegos") {
            // pantalla donde se muestran los juegos
            JuegosScreen(
                onLogout = {
                    // cuando aprietas "logout"
                    navController.navigate("login") {
                        popUpTo("juegos") { inclusive = true }
                        // borra juegos del historial
                    }
                },
                onRandomUser = {
                    // cuando entras a ver usuario random
                    navController.navigate("randomuser")
                }
            )
        }

        // ---------- random user ----------
        composable(route = "randomuser") {
            // pantalla que muestra un usuario aleatorio
            RandomUserScreen()
        }
    }
}
