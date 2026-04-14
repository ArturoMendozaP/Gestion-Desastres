package com.example.gestiondesastres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // "Crear cuenta" → lleva a información personal
        view.findViewById<MaterialButton>(R.id.btn_register).setOnClickListener {
            findNavController().navigate(R.id.action_register_to_registerInfo)
        }

        // "Volver al inicio de sesión"
        view.findViewById<MaterialButton>(R.id.btn_back).setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}