package com.example.gamezone.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gamezone.R

@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  logo central
            Image(
                painter = painterResource(id = R.drawable.logo_gamezone),
                contentDescription = "Logo GameZone",
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .padding(bottom = 16.dp)
            )


            //  bienvenida
            Text(
                text = "Bienvenido a GameZone 🎮",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Explora, juega y disfruta con nosotros."
            )
            Spacer(Modifier.height(32.dp))

            //  botones principales
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear Cuenta")
            }
        }
    }
}


