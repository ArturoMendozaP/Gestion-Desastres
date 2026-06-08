package com.example.gestiondesastres.siniestros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondesastres.core.ResponseService
import com.example.gestiondesastres.core.model.SiniestroUI
import com.example.gestiondesastres.core.model.TriageColor
import com.example.gestiondesastres.core.model.toUI
import com.example.gestiondesastres.core.repositories.SiniestroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch

class SiniestroViewModel : ViewModel() {

    private val repository = SiniestroRepository()

    private val _siniestros = MutableStateFlow<List<SiniestroUI>>(emptyList())

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Bandera para no volver a llamar a Mockbin si ya cargamos.
    private var yaCargado = false


    // Pantalla 1: SIN CLASIFICAR. Orden FIFO: el más antiguo primero.
    val sinClasificar: StateFlow<List<SiniestroUI>> = _siniestros
        .map { lista ->
            lista.filter { it.triage == null }
                .sortedBy { it.fechaCreacion } // ISO 8601 ordena bien como String
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Pantalla 2a: TRIAGE (clasificados, no atendidos). Rojo -> Amarillo -> Verde.
    val triage: StateFlow<List<SiniestroUI>> = _siniestros
        .map { lista ->
            lista.filter { it.triage != null && !it.atendido }
                .sortedBy { it.triage?.peso }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Pantalla 2b: ATENDIDOS (van al fondo).
    val atendidos: StateFlow<List<SiniestroUI>> = _siniestros
        .map { lista -> lista.filter { it.atendido } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Descarga UNA sola vez al abrir la app.
    fun cargarSiniestrosUnaVez() {
        if (yaCargado) return
        cargarSiniestros()
    }

    // Permite reintentar manualmente (botón "Reintentar").
    fun cargarSiniestros() {
        viewModelScope.launch {
            _cargando.value = true
            _error.value = null
            when (val resultado = repository.getSiniestros()) {
                is ResponseService.Success -> {
                    _siniestros.value = resultado.data?.map { it.toUI() } ?: emptyList()
                    yaCargado = true
                }
                is ResponseService.Error -> {
                    _error.value = resultado.message
                }
                is ResponseService.Loading -> Unit
            }
            _cargando.value = false
        }
    }

    // Busca un siniestro por id (lo usa la DetailActivity).
    fun obtenerPorId(reporteId: String): SiniestroUI? {
        return _siniestros.value.find { it.reporteId == reporteId }
    }

    // Clasifica un evento. Modifica la lista en memoria -> las pantallas reaccionan solas.
    fun clasificar(reporteId: String, color: TriageColor) {
        _siniestros.value = _siniestros.value.map {
            if (it.reporteId == reporteId) it.copy(triage = color) else it
        }
    }

    // Marca como atendido -> pasa al contenedor "Atendidos".
    fun marcarAtendido(reporteId: String) {
        _siniestros.value = _siniestros.value.map {
            if (it.reporteId == reporteId) it.copy(atendido = true) else it
        }
    }
}