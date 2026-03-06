package com.example.mindpulse.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mindpulse.R;
import com.example.mindpulse.data.model.Meditation;
import com.example.mindpulse.databinding.FragmentExploreBinding;
import com.example.mindpulse.ui.adapter.MeditationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment implements MeditationAdapter.OnItemClickListener {

    private FragmentExploreBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        binding.rvExplore.setLayoutManager(new LinearLayoutManager(getContext()));
        
        List<Meditation> list = new ArrayList<>();
        list.add(new Meditation("Anxiety Relief", "Meditation", "12 mins"));
        list.add(new Meditation("Rainy Night", "Sleep", "45 mins"));
        list.add(new Meditation("Classical Study", "Music", "60 mins"));
        list.add(new Meditation("Nature Walk", "Ambient", "20 mins"));
        list.add(new Meditation("Body Scan", "Meditation", "15 mins"));
        
        MeditationAdapter adapter = new MeditationAdapter(list, this);
        binding.rvExplore.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Meditation meditation) {
        Bundle args = new Bundle();
        args.putString("title", meditation.getTitle());
        args.putString("category", meditation.getCategory());
        args.putString("duration", meditation.getDuration());
        Navigation.findNavController(requireView()).navigate(R.id.action_explore_to_details, args);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}