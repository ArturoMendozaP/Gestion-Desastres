package com.example.gestiondesastres.core.model

// Los tres niveles de clasificación TRIAGE.
enum class TriageColor(val peso: Int) {
    ROJO(0),      // Atención prioritaria
    AMARILLO(1),  // Atención media
    VERDE(2)      // No urgente
}
data class SiniestroUI(
    val reporteId: String,
    val tipo: String,
    val subtipo: String,
    val nivelPrioridadOriginal: String,
    val descripcion: String,
    val fechaCreacion: String,
    val direccionCompleta: String,
    val alcaldia: String,
    val colonia: String,
    val latitud: Double,
    val longitud: Double,
    val fotoUrl: String?,
    var triage: TriageColor? = null,
    var atendido: Boolean = false
)