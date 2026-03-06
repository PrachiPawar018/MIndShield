package com.example.mindshield.ui.emergency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mindshield.databinding.FragmentEmergencyBinding
import com.example.mindshield.utils.Resource
import com.google.firebase.auth.FirebaseAuth

class EmergencyFragment : Fragment() {

    private var _binding: FragmentEmergencyBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: EmergencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmergencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        binding.btnSendAlert.setOnClickListener {
            viewModel.sendAlert(userId, "general", "Emergency assistance needed!")
        }

        viewModel.alertState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.btnSendAlert.isEnabled = false
                }
                is Resource.Success -> {
                    binding.btnSendAlert.isEnabled = true
                    Toast.makeText(context, "Emergency alert sent!", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    binding.btnSendAlert.isEnabled = true
                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}