package com.example.gestiondesastres.core.repositories

import com.example.gestiondesastres.core.ResponseService
import com.example.gestiondesastres.core.model.SiniestroResponse
import com.example.gestiondesastres.core.network.ApiClient
import com.example.gestiondesastres.core.network.SiniestroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SiniestroRepository : SiniestroService {
    private val api = ApiClient.SiniestroApi

    override suspend fun getSiniestros(): ResponseService<List<SiniestroResponse>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getSiniestros()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ResponseService.Success(body.results)
                } else {
                    ResponseService.Error("Respuesta vacía del servidor")
                }
            } else {
                ResponseService.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            // Manejamos la excepción por si no hay internet o el servidor cae
            ResponseService.Error(e.message ?: "Ocurrió un error inesperado al conectar")
        }
    }
}