package com.example.gestiondesastres.siniestros

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesastres.R
import com.example.gestiondesastres.core.model.SiniestroUI
import com.example.gestiondesastres.core.model.TriageColor

class TriageAdapter(
    private var items: List<SiniestroUI>,
    private val mostrarBotonAtender: Boolean,
    private val onAtender: (SiniestroUI) -> Unit
) : RecyclerView.Adapter<TriageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val barra: View = view.findViewById(R.id.view_barra_color)
        val tvTipo: TextView = view.findViewById(R.id.tv_triage_tipo)
        val tvAlcaldia: TextView = view.findViewById(R.id.tv_triage_alcaldia)
        val btnAtender: Button = view.findViewById(R.id.btn_atender)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_triage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTipo.text = "${item.tipo} · ${item.subtipo}"
        holder.tvAlcaldia.text = item.alcaldia

        val color = when (item.triage) {
            TriageColor.ROJO -> Color.parseColor("#D32F2F")
            TriageColor.AMARILLO -> Color.parseColor("#F9A825")
            TriageColor.VERDE -> Color.parseColor("#2E7D32")
            null -> Color.GRAY
        }
        holder.barra.setBackgroundColor(color)

        if (mostrarBotonAtender) {
            holder.btnAtender.visibility = View.VISIBLE
            holder.btnAtender.setOnClickListener { onAtender(item) }
        } else {
            holder.btnAtender.visibility = View.GONE
        }
    }

    override fun getItemCount() = items.size

    fun actualizar(nuevos: List<SiniestroUI>) {
        items = nuevos
        notifyDataSetChanged()
    }
}