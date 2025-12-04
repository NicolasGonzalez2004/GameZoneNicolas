package com.example.gamezone.ui.auth
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamezone.R
import com.example.gamezone.viewmodel.AuthViewModel



@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit, // callback cuando haces login bien
    onCreateAccount: () -> Unit, // callback para ir a registro
    viewModel: AuthViewModel = viewModel() // obtiene el viewmodel
) {

    val uiState by viewModel.uiState.collectAsState()
    // observa el estado del viewmodel (email, password, errores)

    Box(
        modifier = Modifier
            .fillMaxSize()           // ocupa toda la pantalla
            .padding(24.dp),         // deja espacio a los bordes
        contentAlignment = Alignment.Center // centra todo
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(), // columna grande
            horizontalAlignment = Alignment.CenterHorizontally // centro horizontal
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_gamezone), // carga imagen
                contentDescription = "Logo GameZone",
                modifier = Modifier
                    .size(220.dp)      // tamaño de la imagen
                    .clip(CircleShape) // forma circular
                    .padding(bottom = 16.dp) // espacio abajo
            )

            Text(
                text = "Bienvenido a GameZone 🎮",
                style = MaterialTheme.typography.headlineSmall
                // texto de bienvenida
            )

            Spacer(Modifier.height(16.dp)) // espacio

            OutlinedTextField(
                value = uiState.email,                // email actual
                onValueChange = { viewModel.updateField("email", it) },
                // cuando escribes, actualiza el email en el viewmodel
                label = { Text("Correo electrónico") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp)) // espacio pequeño

            OutlinedTextField(
                value = uiState.password,            // password actual
                onValueChange = { viewModel.updateField("password", it) },
                // actualiza password
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                // oculta los caracteres
                modifier = Modifier.fillMaxWidth()
            )

            if (uiState.errors["login"] != null) {
                // si hay un error de login, lo muestra
                Spacer(Modifier.height(8.dp))
                Text(
                    text = uiState.errors["login"]!!,
                    color = MaterialTheme.colorScheme.error, // color rojo
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.login()                      // intenta login
                    if (viewModel.uiState.value.success)   // si salió bien
                        onLoginSuccess()                   // va a juegos
                },
                enabled = uiState.email.isNotBlank() &&
                        uiState.password.isNotBlank(),
                // botón activo si los campos no están vacíos
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }

            Spacer(Modifier.height(32.dp))

            TextButton(onClick = onCreateAccount) {
                // botón para ir a crear cuenta
                Text("Crear cuenta")
            }
        }
    }
}
