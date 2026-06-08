package com.example.gestiondesastres.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gestiondesastres.R
import com.example.gestiondesastres.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvNombre = view.findViewById<TextView>(R.id.tv_nombre)
        val tvUsuario = view.findViewById<TextView>(R.id.tv_usuario)
        val tvTelefono = view.findViewById<TextView>(R.id.tv_telefono)
        val progress = view.findViewById<ProgressBar>(R.id.progress_cuenta)

        viewLifecycleOwner.lifecycleScope.launch {
            progress.visibility = View.VISIBLE
            val perfil = obtenerPerfil()
            progress.visibility = View.GONE

            if (perfil != null) {
                tvNombre.text = "Nombre: ${perfil.firstName} ${perfil.lastName}"
                tvUsuario.text = "Usuario: ${perfil.username}"
                tvTelefono.text = "Teléfono: ${perfil.phoneNumber}"
            } else {
                tvNombre.text = "No se pudieron cargar los datos"
            }
        }
    }

    // Lee el documento del usuario actual desde Firestore, usando corrutinas.
    private suspend fun obtenerPerfil(): UserProfile? = withContext(Dispatchers.IO) {
        try {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@withContext null
            val doc = FirebaseFirestore.getInstance()
                .collection("users").document(uid).get().await()
            doc.toObject(UserProfile::class.java)
        } catch (e: Exception) {
            null
        }
    }
}