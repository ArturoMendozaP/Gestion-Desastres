package com.example.gestiondesastres.siniestros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesastres.R
import com.example.gestiondesastres.core.model.SiniestroUI

class SinClasificarAdapter(
    private var items: List<SiniestroUI>,
    private val onClick: (SiniestroUI) -> Unit
) : RecyclerView.Adapter<SinClasificarAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTipo: TextView = view.findViewById(R.id.tv_item_tipo)
        val tvAlcaldia: TextView = view.findViewById(R.id.tv_item_alcaldia)
        val tvFecha: TextView = view.findViewById(R.id.tv_item_fecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_siniestro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTipo.text = "${item.tipo} · ${item.subtipo}"
        holder.tvAlcaldia.text = item.alcaldia
        holder.tvFecha.text = item.fechaCreacion
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size

    fun actualizar(nuevos: List<SiniestroUI>) {
        items = nuevos
        notifyDataSetChanged()
    }
}