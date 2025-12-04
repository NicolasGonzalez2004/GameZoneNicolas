package com.example.gamezone.ui.auth
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamezone.viewmodel.AuthViewModel



@Composable
fun RegisterScreen(
    onRegisterDone: () -> Unit,      // función que vuelve al login
    viewModel: AuthViewModel = viewModel()   // obtiene el viewmodel
) {

    val uiState by viewModel.uiState.collectAsState()
    // estado con los campos, errores y success

    Column(
        modifier = Modifier
            .fillMaxSize()      // ocupa toda la pantalla
            .padding(24.dp)     // deja espacio a los lados
    ) {

        Text(
            text = "crear cuenta",
            style = MaterialTheme.typography.headlineSmall
            // título de la pantalla
        )

        Spacer(Modifier.height(16.dp))

        // -------- campo nombre --------
        OutlinedTextField(
            value = uiState.name,                             // texto escrito
            onValueChange = { viewModel.updateField("name", it) }, // actualiza en el viewmodel
            label = { Text("nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["name"]?.let { ErrorText(it) } // muestra error si hay

        Spacer(Modifier.height(8.dp))

        // -------- campo correo --------
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateField("email", it) },
            label = { Text("correo @duoc.cl") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["email"]?.let { ErrorText(it) }

        Spacer(Modifier.height(8.dp))

        // -------- campo contraseña --------
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.updateField("password", it) },
            label = { Text("contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["password"]?.let { ErrorText(it) }

        Spacer(Modifier.height(8.dp))

        // -------- campo confirmar contraseña --------
        OutlinedTextField(
            value = uiState.confirmPassword,
            onValueChange = { viewModel.updateField("confirmPassword", it) },
            label = { Text("confirmar contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errors["confirmPassword"]?.let { ErrorText(it) }

        Spacer(Modifier.height(16.dp))

        // -------- título géneros --------
        Text(
            text = "tipo de género",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(8.dp))

        // listas con géneros para marcar
        val generosColumna1 = listOf(
            "ficción", "misterio", "fantasía", "ciencia ficción", "romance"
        )
        val generosColumna2 = listOf(
            "terror", "histórico", "aventura", "biografía"
        )

        // caja que contiene los géneros
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
                // -------- columna izquierda --------
                Column(modifier = Modifier.weight(1f)) {
                    generosColumna1.forEach { genero ->
                        GenreCheckbox(
                            label = genero,                      // nombre del género
                            checked = uiState.genres.contains(genero), // si está marcado
                            onCheckedChange = {
                                viewModel.toggleGenre(genero)     // agrega o quita el género
                            }
                        )
                    }
                }

                Spacer(Modifier.width(16.dp))

                // -------- columna derecha --------
                Column(modifier = Modifier.weight(1f)) {
                    generosColumna2.forEach { genero ->
                        GenreCheckbox(
                            label = genero,
                            checked = uiState.genres.contains(genero),
                            onCheckedChange = {
                                viewModel.toggleGenre(genero)
                            }
                        )
                    }
                }
            }
        }

        uiState.errors["genres"]?.let {
            Spacer(Modifier.height(4.dp))
            ErrorText(it) // error si no escogió mínimo 1 género
        }

        Spacer(Modifier.height(24.dp))

        // -------- botón de registrar --------
        Button(
            onClick = { viewModel.register() },       // manda a validar registro
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("registrarme")
        }

        // si salió bien, vuelve a login automáticamente
        if (uiState.success) {
            LaunchedEffect(Unit) {
                onRegisterDone()
            }
        }
    }
}

// ---------- checkbox de géneros ----------
@Composable
fun GenreCheckbox(
    label: String,                  // nombre del género
    checked: Boolean,               // si está marcado o no
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Start // deja todo a la izquierda
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(Modifier.width(4.dp))
        Text(text = label)
    }
}

// ---------- texto de error ----------
@Composable
fun ErrorText(msg: String) {
    Text(
        text = msg,
        color = MaterialTheme.colorScheme.error,       // rojo
        style = MaterialTheme.typography.bodySmall     // texto pequeño
    )
}
