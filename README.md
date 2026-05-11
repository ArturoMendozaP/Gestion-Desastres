# Red Ciudadana de Reporte de Siniestros 🚨

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-039BE5?style=for-the-badge&logo=Firebase&logoColor=white)

## 📋 Descripción del Proyecto
Esta aplicación móvil nativa para Android surge como una solución tecnológica para la **gestión y reporte de incidentes críticos** por parte de la ciudadanía durante desastres naturales o emergencias urbanas. 

A través de un sistema de *crowdsourcing*, los usuarios pueden emitir alertas geolocalizadas sobre siniestros específicos (fugas de gas, incendios, derrumbes) derivados de desastres mayores (terremotos, inundaciones, etc.). La aplicación está diseñada bajo una filosofía **Offline-First**, permitiendo que la ayuda y la información fluyan incluso cuando la infraestructura de red es inestable.

---

## ✨ Características Principales
- **Registro de Ciudadanos:** Autenticación segura y gestión de perfiles mediante Firebase.
- **Reportes Inteligentes:** Flujo de reporte rápido categorizado por tipo de desastre y siniestro.
- **Geolocalización Automática:** Captura de coordenadas GPS exactas para una respuesta precisa.
- **Evidencia Visual Obligatoria:** Integración con la cámara para captura de fotos en tiempo real, garantizando la veracidad del reporte.
- **Sincronización Diferida:** Los reportes se guardan localmente si no hay conexión y se suben automáticamente a la nube (Firestore) al recuperar la señal.
- **Mapa de Riesgo:** Visualización interactiva de incidentes reportados por la comunidad en zonas cercanas.

---

## 🛠️ Stack Tecnológico
- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **Arquitectura:** MVVM (Model-View-ViewModel) para una separación clara de responsabilidades.
- **Asincronía:** Kotlin Coroutines & Flow para procesos en segundo plano.
- **Backend:** Firebase (Authentication, Cloud Firestore).
- **Mapas y Ubicación:** Google Maps SDK & Fused Location Provider API.
- **Navegación:** Jetpack Navigation Component.

---

## 🏗️ Estructura del Proyecto
El desarrollo sigue los estándares de arquitectura moderna en Android:
- **UI (Fragments/Activities):** Interfaces reactivas que observan el estado de los ViewModels.
- **ViewModels:** Gestión del estado de la interfaz y lógica de negocio.
- **Repositories:** Capa de abstracción de datos (remotos y locales).
- **Services:** Manejo de respuestas genéricas y estados de carga (`Loading`, `Success`, `Error`).

---

## 👤 Autor
**Arturo Mendoza P.** 
