package com.example.gestiondesastres.core.model

fun SiniestroResponse.toUI(): SiniestroUI {

    val triageInicial = when (this.siniestro.nivelPrioridad.trim().lowercase()) {
        "rojo" -> TriageColor.ROJO
        "amarillo" -> TriageColor.AMARILLO
        "verde" -> TriageColor.VERDE
        else -> null
    }

    // Si el estatus dice "Atendido", arranca en el contenedor de atendidos.
    val atendidoInicial = this.estatus.trim().equals("Atendido", ignoreCase = true)

    return SiniestroUI(
        reporteId = this.reporteId,
        tipo = this.siniestro.tipo,
        subtipo = this.siniestro.subtipo,
        nivelPrioridadOriginal = this.siniestro.nivelPrioridad,
        descripcion = this.siniestro.descripcion,
        fechaCreacion = this.fechaCreacion,
        direccionCompleta = this.ubicacion.direccionCompleta,
        alcaldia = this.ubicacion.alcaldia,
        colonia = this.ubicacion.colonia,
        latitud = this.ubicacion.coordenadas.latitud,
        longitud = this.ubicacion.coordenadas.longitud,
        fotoUrl = this.evidencia.fotoUrl,
        triage = triageInicial,
        atendido = atendidoInicial
    )
}