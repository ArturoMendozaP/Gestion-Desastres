package com.example.gestiondesastres.onboarding

import android.content.Intent
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
import com.example.gestiondesastres.onboarding.RegisterInfoViewModel
import com.example.gestiondesastres.core.ResponseService
import com.example.gestiondesastres.home.HomeActivity
import com.example.gestiondesastres.models.UserProfile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterInfoFragment : Fragment() {

    private val viewModel: RegisterInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etFirstName = view.findViewById<TextInputEditText>(R.id.et_first_name)
        val etLastName = view.findViewById<TextInputEditText>(R.id.et_last_name)
        val etUsername = view.findViewById<TextInputEditText>(R.id.et_username)
        val etPhone = view.findViewById<TextInputEditText>(R.id.et_phone)
        val etBirthDate = view.findViewById<TextInputEditText>(R.id.et_birthdate)
        val btnSave = view.findViewById<MaterialButton>(R.id.btn_save_info)

        btnSave.setOnClickListener {
            val name = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val birthDate = etBirthDate.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val profile = UserProfile(name, lastName, username, phone, birthDate)
                viewModel.saveProfile(profile)
            } else {
                Toast.makeText(requireContext(), "Por favor llena los campos obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.saveState.collect { state ->
                    when (state) {
                        is ResponseService.Loading -> {
                            btnSave.isEnabled = false
                        }
                        is ResponseService.Success -> {
                            btnSave.isEnabled = true
                            Toast.makeText(requireContext(), "¡Bienvenido!", Toast.LENGTH_SHORT).show()

                            activity?.let { miActividad ->
                                val intent = Intent(miActividad, HomeActivity::class.java)
                                startActivity(intent)
                                miActividad.finish()
                            }
                        }
                        is ResponseService.Error -> {
                            btnSave.isEnabled = true
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                        }
                        null -> Unit
                    }
                }
            }
        }
    }
}