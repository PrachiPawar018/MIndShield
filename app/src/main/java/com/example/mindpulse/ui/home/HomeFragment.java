package com.example.mindpulse.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mindpulse.R;
import com.example.mindpulse.data.model.Meditation;
import com.example.mindpulse.databinding.FragmentHomeBinding;
import com.example.mindpulse.ui.adapter.MeditationAdapter;
import com.example.mindpulse.ui.auth.AuthViewModel;

public class HomeFragment extends Fragment implements MeditationAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupRecyclerView();
        observeViewModel();
    }

    private void setupRecyclerView() {
        binding.rvRecommendations.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void observeViewModel() {
        homeViewModel.getMeditations().observe(getViewLifecycleOwner(), meditations -> {
            MeditationAdapter adapter = new MeditationAdapter(meditations, this);
            binding.rvRecommendations.setAdapter(adapter);
        });

        authViewModel.getUserLiveData().observe(getViewLifecycleOwner(), firebaseUser -> {
            if (firebaseUser != null) {
                String name = firebaseUser.getDisplayName();
                if (name == null || name.isEmpty()) {
                    name = "User";
                }
                binding.tvWelcome.setText("Welcome back, " + name + "!");
            }
        });
    }

    @Override
    public void onItemClick(Meditation meditation) {
        Bundle args = new Bundle();
        args.putString("title", meditation.getTitle());
        args.putString("category", meditation.getCategory());
        args.putString("duration", meditation.getDuration());
        Navigation.findNavController(requireView()).navigate(R.id.action_home_to_details, args);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}