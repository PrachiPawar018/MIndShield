package com.example.mindpulse.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mindpulse.R
import com.example.mindpulse.databinding.FragmentProfileBinding
import com.example.mindpulse.ui.auth.AuthViewModel
import com.example.mindpulse.ui.auth.AuthViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.btnLogout.setOnClickListener {
            authViewModel.logout()
        }

        binding.btnEditProfile.setOnClickListener {
            // Placeholder for edit profile
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_settings)
        }

        binding.btnEmergencyContacts.setOnClickListener {
            findNavController().navigate(R.id.navigation_emergency)
        }
    }

    private fun observeViewModel() {
        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            if (user == null) {
                findNavController().navigate(R.id.navigation_login)
            } else {
                binding.tvName.text = user.fullName.ifEmpty { "User Name" }
                binding.tvEmail.text = user.email
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
