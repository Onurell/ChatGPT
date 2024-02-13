package com.example.firebase

import LoginFragment
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebase.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        editTextEmail = view.findViewById(R.id.email)
        editTextPassword = view.findViewById(R.id.password)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        val loginNow = view.findViewById<TextView>(R.id.loginNow)
        loginNow.setOnClickListener {
            findNavController().navigate(R.id.action_dataFragment_to_homeFragment)
        }
    }

    private fun registerUser() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(requireContext(), "Enter Email", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(requireContext(), "Enter Password", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Authentication Success.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_dataFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}