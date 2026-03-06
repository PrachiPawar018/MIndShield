package com.example.mindpulse.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mindpulse.R
import com.example.mindpulse.databinding.FragmentSplashBinding
import com.example.mindpulse.ui.auth.AuthViewModel
import com.example.mindpulse.ui.auth.AuthViewModelFactory

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by activityViewModels {
        AuthViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivLogo.alpha = 0f
        binding.ivLogo.animate().alpha(1f).setDuration(1000).withEndAction {
            checkAuthStatus()
        }.start()
    }

    private fun checkAuthStatus() {
        if (authViewModel.currentUser.value != null) {
            findNavController().navigate(R.id.action_splash_to_home)
        } else {
            findNavController().navigate(R.id.action_splash_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}