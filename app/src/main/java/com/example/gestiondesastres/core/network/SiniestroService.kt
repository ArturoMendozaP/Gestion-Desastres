package com.example.gestiondesastres.core.network

import com.example.gestiondesastres.core.ResponseService
import com.example.gestiondesastres.core.model.SiniestroResponse

interface SiniestroService {
    suspend fun getSiniestros(): ResponseService<List<SiniestroResponse>>
}