package com.example.mindpulse.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mindpulse.R;
import com.example.mindpulse.databinding.FragmentSignupBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignupBinding binding;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        observeViewModel();

        binding.btnSignUp.setOnClickListener(v -> {
            String fullName = binding.etFullName.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (validateInput(fullName, email, password)) {
                authViewModel.signUp(fullName, email, password);
            }
        });

        binding.tvLogin.setOnClickListener(v -> 
            Navigation.findNavController(view).navigateUp()
        );
    }

    private boolean validateInput(String fullName, String email, String password) {
        boolean isValid = true;
        if (fullName.isEmpty()) {
            binding.tilFullName.setError("Full name is required");
            isValid = false;
        } else {
            binding.tilFullName.setError(null);
        }

        if (email.isEmpty()) {
            binding.tilEmail.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("Please enter a valid email");
            isValid = false;
        } else {
            binding.tilEmail.setError(null);
        }

        if (password.isEmpty()) {
            binding.tilPassword.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            binding.tilPassword.setError("Password must be at least 6 characters");
            isValid = false;
        } else {
            binding.tilPassword.setError(null);
        }

        return isValid;
    }

    private void observeViewModel() {
        authViewModel.getUserLiveData().observe(getViewLifecycleOwner(), firebaseUser -> {
            if (firebaseUser != null) {
                Navigation.findNavController(requireView()).navigate(R.id.action_signup_to_home);
            }
        });

        authViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        authViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.btnSignUp.setEnabled(!isLoading);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}