package com.example.mindpulse.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mindpulse.R;
import com.example.mindpulse.databinding.FragmentSplashBinding;
import com.example.mindpulse.ui.auth.AuthViewModel;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Animate logo or text if needed
        binding.ivLogo.setAlpha(0f);
        binding.ivLogo.animate().alpha(1f).setDuration(1000).start();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (isAdded()) {
                checkUserSession();
            }
        }, 2000);
    }

    private void checkUserSession() {
        if (authViewModel.getUserLiveData().getValue() != null) {
            Navigation.findNavController(requireView()).navigate(R.id.action_splash_to_home);
        } else {
            Navigation.findNavController(requireView()).navigate(R.id.action_splash_to_login);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}