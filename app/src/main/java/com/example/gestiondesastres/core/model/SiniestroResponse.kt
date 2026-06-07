package com.example.gestiondesastres.core.model

import com.google.gson.annotations.SerializedName

// Este envoltorio atrapa el arreglo "results" que manda tu JSON de Mockbin
data class SiniestroDataWrapper(
    @SerializedName("results") val results: List<SiniestroResponse>
)

data class SiniestroResponse(
    @SerializedName("reporte_id") val reporteId: String,
    @SerializedName("usuario_id") val usuarioId: String,
    @SerializedName("estatus") val estatus: String,
    @SerializedName("fecha_creacion") val fechaCreacion: String,
    @SerializedName("siniestro") val siniestro: SiniestroDetalle,
    @SerializedName("evidencia") val evidencia: Evidencia,
    @SerializedName("ubicacion") val ubicacion: Ubicacion,
    @SerializedName("metadatos_dispositivo") val metadatosDispositivo: MetadatosDispositivo
)

data class SiniestroDetalle(
    @SerializedName("tipo") val tipo: String,
    @SerializedName("subtipo") val subtipo: String,
    @SerializedName("nivel_prioridad") val nivelPrioridad: String,
    @SerializedName("descripcion") val descripcion: String
)

data class Evidencia(
    @SerializedName("foto_url") val fotoUrl: String?,
    @SerializedName("video_url") val videoUrl: String?
)

data class Ubicacion(
    @SerializedName("direccion_completa") val direccionCompleta: String,
    @SerializedName("calle_numero") val calleNumero: String,
    @SerializedName("colonia") val colonia: String,
    @SerializedName("codigo_postal") val codigoPostal: String,
    @SerializedName("alcaldia") val alcaldia: String,
    @SerializedName("coordenadas") val coordenadas: Coordenadas,
    @SerializedName("referencias") val referencias: String?
)

data class Coordenadas(
    @SerializedName("latitud") val latitud: Double,
    @SerializedName("longitud") val longitud: Double
)

data class MetadatosDispositivo(
    @SerializedName("bateria_nivel") val bateriaNivel: Int,
    @SerializedName("precision_gps_metros") val precisionGpsMetros: Double
)