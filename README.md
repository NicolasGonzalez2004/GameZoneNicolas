# 🎮 GameZone – App Móvil Android + Microservicio + API Externa

Proyecto desarrollado en **Kotlin + Jetpack Compose**, siguiendo arquitectura **MVVM**, consumo de API REST mediante **Retrofit**, e integración con una **API externa (RandomUser)**.



---

##  Integrantes


|Nicolás Eduardo González Monsalvez

#  Funcionalidades principales

### ✔ Autenticación
- Pantalla de **Login**
- Pantalla de **Registro**
- Validaciones básicas (correo, contraseña, campos vacíos)

### ✔ Microservicio propio (Backend GameZone)
- Consumo del endpoint **GET /games**  
- Consulta por ID usando **GET /games/{id}**  
- Muestra lista de juegos reales desde la base de datos

### ✔ Listado de Juegos (Pantalla Principal)
- Lista con **LazyColumn**
- Diseño con tarjetas interactivas (animación scale/elevation)
- Carga de imágenes con **Coil**

### ✔ API Externa – RandomUser
- Obtiene un usuario aleatorio desde:  
  `https://randomuser.me/api/`
- Muestra foto, nombre, email y país del jugador
- Manejo de estados: loading, error, retry

### ✔ Navegación completa (Navigation Compose)
- Start → Login → Registro → Juegos → Jugador Aleatorio → Logout

### ✔ Arquitectura MVVM
- ViewModel + StateFlow  
- Repository pattern  
- Retrofit + corrutinas  
- Clase UiState para cada vista

### ✔ APK firmado + Llave .jks
- Proyecto compilado en modo **release**
- APK firmado  
- Archivo `.jks` 








