package com.example.mindpulse.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mindpulse.R;
import com.example.mindpulse.databinding.FragmentProfileBinding;
import com.example.mindpulse.ui.auth.AuthViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupUI();
        observeViewModel();
    }

    private void setupUI() {
        binding.btnLogout.setOnClickListener(v -> {
            authViewModel.logout();
        });

        binding.btnEditProfile.setOnClickListener(v -> {
            // Placeholder for edit profile
        });

        binding.btnSettings.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_settings);
        });
    }

    private void observeViewModel() {
        authViewModel.getUserLiveData().observe(getViewLifecycleOwner(), firebaseUser -> {
            if (firebaseUser == null) {
                Navigation.findNavController(requireView()).navigate(R.id.action_login_to_home); // Assuming global action or specific logout handling
                // Actually, since this is ProfileFragment, we should navigate to Login
                Navigation.findNavController(requireView()).navigate(R.id.navigation_login);
            } else {
                binding.tvEmail.setText(firebaseUser.getEmail());
                // Set name if available in user object/Firestore
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}