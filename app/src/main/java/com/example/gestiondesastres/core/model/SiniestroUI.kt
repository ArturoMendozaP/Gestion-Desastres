package com.example.gestiondesastres.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Los tres niveles de clasificación TRIAGE.
// El "peso" nos sirve para ordenar: Rojo primero, Verde al último.
enum class TriageColor(val peso: Int) {
    ROJO(0),      // Atención prioritaria
    AMARILLO(1),  // Atención media
    VERDE(2)      // No urgente
}

// Envoltorio local. Contiene el dato original del API (inmutable)
// más los campos que SOLO viven en memoria local: triage y atendido.
// Es Parcelable para poder mandarlo por Intent a la DetailActivity si hiciera falta,
// aunque nosotros mandaremos solo el reporteId.
@Parcelize
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
) : Parcelable