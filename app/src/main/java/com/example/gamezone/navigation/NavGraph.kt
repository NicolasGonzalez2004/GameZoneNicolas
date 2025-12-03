package com.example.gamezone.navigation

import androidx.navigation.NavController

object NavGraph {

    fun navigateToLogin(navController: NavController) {
        navController.navigate("login")
    }

    fun navigateToRegister(navController: NavController) {
        navController.navigate("register")
    }

    fun navigateToHome(navController: NavController) {
        navController.navigate("home")
    }
}

