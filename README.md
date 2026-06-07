# Sistema de Gestión y Triage de Siniestros (Protección Civil) 🚨

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-000000?style=for-the-badge&logo=retrofit&logoColor=white)

## 📋 Descripción del Proyecto
Plataforma administrativa diseñada para equipos de **Protección Civil**, enfocada en la gestión eficiente, priorización y respuesta ante siniestros reportados por la ciudadanía. La aplicación centraliza el flujo de información, permitiendo a los operadores clasificar eventos en tiempo real bajo criterios de importancia (Triage), asegurando que los incidentes críticos reciban atención inmediata.

---

## ✨ Características Principales
- **Bandeja de Entrada (FIFO):** Visualización de eventos recién creados ordenados por tiempo de llegada.
- **Sistema de Triage:** Clasificación de siniestros por niveles de prioridad (Rojo, Amarillo, Verde) para optimizar la toma de decisiones.
- **Gestión de Ciclo de Vida:** Control de estatus de siniestros (Nuevo, En proceso, Atendido).
- **Vista de Detalle:** Acceso total a los datos del siniestro, evidencia multimedia y ubicación precisa del evento.
- **Gestión de Cuenta:** Acceso a perfil de usuario con datos registrados en Firebase.

---

## 🛠️ Stack Tecnológico
- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **Arquitectura:** MVVM (Model-View-ViewModel) para una arquitectura escalable.
- **Networking:** [Retrofit](https://square.github.io/retrofit/) para la comunicación con la API de siniestros (vía Mockbin).
- **Asincronía:** Kotlin Coroutines & Flow.
- **Backend:** Firebase (Authentication).
- **Navegación:** Jetpack Navigation Component.

---

## 🏗️ Estructura del Proyecto
- **UI:** Interfaces reactivas (Fragments) orientadas a la gestión de datos.
- **ViewModels:** Lógica de estado para la clasificación y filtrado de siniestros.
- **Network Layer:** Cliente configurado con Interceptors para monitoreo de tráfico.
- **Repositories:** Capa de datos que coordina la API y las reglas de negocio.

---

## 👤 Autor
**Arturo Mendoza P.**
