package com.example.gestiondesastres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

private const val ARG_EMAIL = "email"
private const val ARG_UID = "uid"

class RegisterInfoFragment : Fragment() {

    private var email: String? = null
    private var uid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL)
            uid = it.getString(ARG_UID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // "Continuar" → lleva al Home
        view.findViewById<MaterialButton>(R.id.btn_continue).setOnClickListener {
            findNavController().navigate(R.id.action_registerInfo_to_home)
        }

        // "Completar después" → también lleva al Home
        view.findViewById<MaterialButton>(R.id.btn_skip).setOnClickListener {
            findNavController().navigate(R.id.action_registerInfo_to_home)
        }
    }

    companion object {
        fun newInstance(email: String, uid: String) =
            RegisterInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_UID, uid)
                }
            }
    }
}