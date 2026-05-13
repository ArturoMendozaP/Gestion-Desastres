package com.example.gestiondesastres.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gestiondesastres.R
import com.google.android.material.button.MaterialButton

class ForgottenPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgotten_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // "Volver al inicio de sesión"
        view.findViewById<MaterialButton>(R.id.btn_back).setOnClickListener {
            findNavController().navigate(R.id.action_forgot_to_login)
        }
    }

    companion object {
        fun newInstance() = ForgottenPasswordFragment()
    }
}