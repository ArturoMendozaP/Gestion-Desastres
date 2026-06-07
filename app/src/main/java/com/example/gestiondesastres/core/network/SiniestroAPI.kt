package com.example.gestiondesastres.core.network

import com.example.gestiondesastres.core.model.SiniestroDataWrapper
import retrofit2.http.GET
import retrofit2.Response

interface SiniestroAPI {
    @GET("/")
    suspend fun getSiniestros(): Response<SiniestroDataWrapper>
}