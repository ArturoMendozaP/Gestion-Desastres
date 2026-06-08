package com.example.gestiondesastres.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesastres.R
import com.example.gestiondesastres.siniestros.SiniestroViewModel
import com.example.gestiondesastres.siniestros.TriageAdapter
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private val viewModel: SiniestroViewModel by activityViewModels()
    private lateinit var adapterTriage: TriageAdapter
    private lateinit var adapterAtendidos: TriageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTriage = view.findViewById<RecyclerView>(R.id.rv_triage)
        val rvAtendidos = view.findViewById<RecyclerView>(R.id.rv_atendidos)

        // Lista de triage: muestra el botón "Marcar atendido".
        adapterTriage = TriageAdapter(emptyList(), mostrarBotonAtender = true) { siniestro ->
            viewModel.marcarAtendido(siniestro.reporteId)
        }
        // Lista de atendidos: solo lectura, sin botón.
        adapterAtendidos = TriageAdapter(emptyList(), mostrarBotonAtender = false) {}

        rvTriage.layoutManager = LinearLayoutManager(requireContext())
        rvTriage.adapter = adapterTriage
        rvTriage.isNestedScrollingEnabled = false

        rvAtendidos.layoutManager = LinearLayoutManager(requireContext())
        rvAtendidos.adapter = adapterAtendidos
        rvAtendidos.isNestedScrollingEnabled = false

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.triage.collect { adapterTriage.actualizar(it) } }
                launch { viewModel.atendidos.collect { adapterAtendidos.actualizar(it) } }
            }
        }
    }
}