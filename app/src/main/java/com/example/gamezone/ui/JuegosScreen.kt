package com.example.gamezone.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    viewModel: JuegosViewModel = viewModel(),
    onLogout: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarJuegos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // lista
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            Text("Error: ${state.error}")
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(state.juegos) { game ->
                    JuegoItem(juego = game)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // boton cerrar sesion , lo puse abajo
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}

@Composable
fun JuegoItem(juego: Game) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(if (isPressed) 0.97f else 1f)
    val elevation by animateDpAsState(if (isPressed) 2.dp else 6.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(scaleX = scale, scaleY = scale),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        shape = RoundedCornerShape(16.dp),
        interactionSource = interactionSource,
        onClick = {}
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

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
                Text(juego.title, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(juego.description)
                Spacer(Modifier.height(4.dp))
                Text("Precio: ${juego.price} CLP")
            }
        }
    }
}
