package com.example.gestiondesastres.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesastres.R
import com.example.gestiondesastres.home.desastreDetail.DetailActivity
import com.example.gestiondesastres.siniestros.SinClasificarAdapter
import com.example.gestiondesastres.siniestros.SiniestroViewModel
import kotlinx.coroutines.launch

class MapFragment : Fragment() {

    // activityViewModels() = el MISMO ViewModel que comparten todos los fragmentos.
    private val viewModel: SiniestroViewModel by activityViewModels()
    private lateinit var adapter: SinClasificarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rv_sin_clasificar)
        val progress = view.findViewById<ProgressBar>(R.id.progress_sin)
        val tvVacio = view.findViewById<TextView>(R.id.tv_vacio_sin)

        adapter = SinClasificarAdapter(emptyList()) { siniestro ->
            // Al tocar un ítem: lanzamos DetailActivity pasando el id por Intent.
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_REPORTE_ID, siniestro.reporteId)
            startActivity(intent)
        }
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        // Disparamos la carga UNA sola vez.
        viewModel.cargarSiniestrosUnaVez()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.sinClasificar.collect { lista ->
                        adapter.actualizar(lista)
                        tvVacio.visibility = if (lista.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.cargando.collect { cargando ->
                        progress.visibility = if (cargando) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }
}