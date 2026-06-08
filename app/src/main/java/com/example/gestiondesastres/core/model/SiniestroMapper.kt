package com.example.gestiondesastres.core.model

// Convierte el modelo crudo del API en nuestro modelo local de UI.
fun SiniestroResponse.toUI(): SiniestroUI {
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
        fotoUrl = this.evidencia.fotoUrl
    )
}