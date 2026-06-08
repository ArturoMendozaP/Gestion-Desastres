package com.example.gestiondesastres.siniestros

import com.example.gestiondesastres.core.model.SiniestroUI
import kotlinx.coroutines.flow.MutableStateFlow

object SiniestroStore {
    val siniestros = MutableStateFlow<List<SiniestroUI>>(emptyList())
    var yaCargado = false
}