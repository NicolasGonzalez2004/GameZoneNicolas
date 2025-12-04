//pantalla principal donde se muestran los juegos que vienen del backend.
package com.example.gamezone.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gamezone.data.model.Game
import com.example.gamezone.viewmodel.JuegosViewModel

@Composable
fun JuegosScreen(
    onLogout: () -> Unit,
    onRandomUser: () -> Unit,
    viewModel: JuegosViewModel = viewModel()
) {
    // escuchamos el estado que expone el viewmodel
    val state by viewModel.uiState.collectAsState()

    // al entrar por primera vez a la pantalla se dispara la carga de juegos
    LaunchedEffect(Unit) {
        viewModel.cargarJuegos()
    }

    // según el estado mostramos loading, error o la lista
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${state.error}")
            }
        }

        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // lista scrollable con los juegos que vienen del backend
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    items(state.juegos) { game ->
                        JuegoItem(juego = game)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // botón que llama a la pantalla / acción de random user (api externa)
                Button(
                    onClick = onRandomUser,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Ver jugador aleatorio (RandomUser API)")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // botón para cerrar sesión y volver al flujo de auth
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Cerrar sesión")
                }
            }
        }
    }
}

@Composable
fun JuegoItem(juego: Game) {

    // fuente de interacción para saber si la card está presionada
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // animamos escala y elevación cuando se aprieta la card
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        label = "cardScale"
    )
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 6.dp,
        label = "cardElevation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            ),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        shape = RoundedCornerShape(16.dp),
        interactionSource = interactionSource,
        onClick = {
            // acá podrías navegar al detalle del juego si quisieras
        }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {

            // imagen del juego cargada desde url con coil
            AsyncImage(
                model = juego.imageUrl,
                contentDescription = juego.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                // título del juego, con texto de respaldo por si viene null
                Text(
                    text = juego.title ?: "Juego sin título",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                // descripción o texto por defecto si falta
                Text(text = juego.description ?: "Sin descripción")
                Spacer(modifier = Modifier.height(4.dp))
                // muestra el precio en clp, si no viene usa 0.0
                Text(text = "Precio: ${juego.price ?: 0.0} CLP")
            }
        }
    }
}
