package com.example.mindpulse.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mindpulse.R
import com.example.mindpulse.databinding.FragmentLoginBinding
import com.example.mindpulse.utils.Resource

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // Manual DI for now as Hilt setup is complex via script
    private val viewModel: AuthViewModel by viewModels {
        AuthViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, Unit: Unit) {
        super.onViewCreated(view, Unit)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                viewModel.login(email, password)
            }
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }

        binding.tvForgotPassword.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (email.isNotEmpty()) {
                viewModel.resetPassword(email)
                Toast.makeText(requireContext(), "Reset link sent to $email", Toast.LENGTH_SHORT).show()
            } else {
                binding.tilEmail.error = getString(R.string.error_email_required)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true
        if (email.isEmpty()) {
            binding.tilEmail.error = getString(R.string.error_email_required)
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = getString(R.string.error_password_required)
            isValid = false
        } else {
            binding.tilPassword.error = null
        }
        return isValid
    }

    private fun observeViewModel() {
        viewModel.authState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnLogin.isEnabled = false
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.isEnabled = true
                    findNavController().navigate(R.id.action_login_to_home)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.isEnabled = true
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}