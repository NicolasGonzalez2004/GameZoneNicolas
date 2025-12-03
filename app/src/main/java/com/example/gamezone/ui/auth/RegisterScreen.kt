package com.example.gamezone.ui.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamezone.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    onRegisterDone: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(text = "Crear cuenta", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        //  nombre
        OutlinedTextField(
            value = uiState.name,
            onValueChange = { viewModel.updateField("name", it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["name"]?.let { ErrorText(it) }

        Spacer(Modifier.height(8.dp))

        //  correo
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateField("email", it) },
            label = { Text("Correo @duoc.cl") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["email"]?.let { ErrorText(it) }

        Spacer(Modifier.height(8.dp))

        //  contraseña
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.updateField("password", it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["password"]?.let { ErrorText(it) }

        Spacer(Modifier.height(8.dp))

        // confirmar contraseña
        OutlinedTextField(
            value = uiState.confirmPassword,
            onValueChange = { viewModel.updateField("confirmPassword", it) },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["confirmPassword"]?.let { ErrorText(it) }

        Spacer(Modifier.height(16.dp))

        //  tipo de genero
        Text(
            text = "Tipo de género",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(8.dp))

        val generosColumna1 = listOf(
            "Ficción",
            "Misterio",
            "Fantasía",
            "Ciencia ficción",
            "Romance"
        )
        val generosColumna2 = listOf(
            "Terror",
            "Histórico",
            "Aventura",
            "Biografía"
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(12.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {

                // Columna 1
                Column(modifier = Modifier.weight(1f)) {
                    generosColumna1.forEach { genero ->
                        GenreCheckbox(
                            label = genero,
                            checked = uiState.genres.contains(genero),
                            onCheckedChange = { _ ->
                                viewModel.toggleGenre(genero)
                            }
                        )
                    }
                }

                Spacer(Modifier.width(16.dp))

                // Columna 2
                Column(modifier = Modifier.weight(1f)) {
                    generosColumna2.forEach { genero ->
                        GenreCheckbox(
                            label = genero,
                            checked = uiState.genres.contains(genero),
                            onCheckedChange = { _ ->
                                viewModel.toggleGenre(genero)
                            }
                        )
                    }
                }
            }
        }

        uiState.errors["genres"]?.let {
            Spacer(Modifier.height(4.dp))
            ErrorText(it)
        }

        Spacer(Modifier.height(24.dp))

        //  boton registrar
        Button(
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarme")
        }

        // Si registramos correctamente  volvemos al login
        if (uiState.success) {
            LaunchedEffect(Unit) {
                onRegisterDone()
            }
        }
    }
}

@Composable
fun GenreCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(Modifier.width(4.dp))
        Text(text = label)
    }
}

@Composable
fun ErrorText(msg: String) {
    Text(
        text = msg,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall
    )
}
