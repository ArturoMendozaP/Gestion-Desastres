package com.example.gestiondesastres.home.desastreDetail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gestiondesastres.R
import com.example.gestiondesastres.core.model.TriageColor
import com.example.gestiondesastres.siniestros.SiniestroViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPORTE_ID = "extra_reporte_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = ViewModelProvider(this)[SiniestroViewModel::class.java]

        val reporteId = intent.getStringExtra(EXTRA_REPORTE_ID)

        val tvTipo = findViewById<TextView>(R.id.tv_detail_tipo)
        val tvDescripcion = findViewById<TextView>(R.id.tv_detail_descripcion)
        val tvUbicacion = findViewById<TextView>(R.id.tv_detail_ubicacion)
        val btnRojo = findViewById<Button>(R.id.btn_rojo)
        val btnAmarillo = findViewById<Button>(R.id.btn_amarillo)
        val btnVerde = findViewById<Button>(R.id.btn_verde)

        val siniestro = reporteId?.let { viewModel.obtenerPorId(it) }

        if (siniestro != null) {
            tvTipo.text = "${siniestro.tipo} · ${siniestro.subtipo}"
            tvDescripcion.text = siniestro.descripcion
            tvUbicacion.text = "${siniestro.direccionCompleta}, ${siniestro.alcaldia}"
        }

        fun clasificarYSalir(color: TriageColor) {
            if (reporteId != null) {
                viewModel.clasificar(reporteId, color)
                Toast.makeText(this, "Clasificado como $color", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        btnRojo.setOnClickListener { clasificarYSalir(TriageColor.ROJO) }
        btnAmarillo.setOnClickListener { clasificarYSalir(TriageColor.AMARILLO) }
        btnVerde.setOnClickListener { clasificarYSalir(TriageColor.VERDE) }
    }
}