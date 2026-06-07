package com.example.gestiondesastres.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.gestiondesastres.R
import com.example.gestiondesastres.onboarding.RegisterViewModel
import com.example.gestiondesastres.core.ResponseService
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etEmail = view.findViewById<TextInputEditText>(R.id.et_email)
        val etPassword = view.findViewById<TextInputEditText>(R.id.et_password)
        val btnRegister = view.findViewById<MaterialButton>(R.id.btn_register)
        val btnBack = view.findViewById<MaterialButton>(R.id.btn_back)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signUp(email, password)
            } else {
                Toast.makeText(requireContext(), "Por favor, ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect { state ->
                    when (state) {
                        is ResponseService.Loading -> {
                            btnRegister.isEnabled = false
                        }
                        is ResponseService.Success -> {
                            btnRegister.isEnabled = true
                            Toast.makeText(requireContext(), "Cuenta creada", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_register_to_registerInfo)
                        }
                        is ResponseService.Error -> {
                            btnRegister.isEnabled = true
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                        }
                        null -> Unit
                    }
                }
            }
        }
    }
}