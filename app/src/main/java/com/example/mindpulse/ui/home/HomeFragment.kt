package com.example.mindpulse.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mindpulse.R
import com.example.mindpulse.databinding.FragmentHomeBinding
import com.example.mindpulse.ui.adapter.MeditationAdapter
import com.example.mindpulse.ui.auth.AuthViewModel
import com.example.mindpulse.ui.auth.AuthViewModelFactory
import com.example.mindpulse.utils.Resource

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    private val authViewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupClickListeners()
        observeViewModels()
    }

    private fun setupClickListeners() {
        binding.btnJournal.setOnClickListener {
            findNavController().navigate(R.id.navigation_journal)
        }

        binding.btnEmergency.setOnClickListener {
            findNavController().navigate(R.id.navigation_emergency)
        }
    }

    private fun setupRecyclerView() {
        binding.rvRecommendations.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModels() {
        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvWelcome.text = getString(R.string.welcome_user, it.fullName.ifEmpty { "User" })
            }
        }

        viewModel.meditations.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show small loading if needed
                }
                is Resource.Success -> {
                    val adapter = MeditationAdapter(resource.data ?: emptyList()) { meditation ->
                        val bundle = Bundle().apply {
                            putString("id", meditation.id)
                            putString("title", meditation.title)
                            putString("category", meditation.category)
                            putString("duration", meditation.duration)
                            putString("description", meditation.description)
                        }
                        findNavController().navigate(R.id.action_home_to_details, bundle)
                    }
                    binding.rvRecommendations.adapter = adapter
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}