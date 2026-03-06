package com.example.mindpulse.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mindpulse.data.model.Meditation;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Meditation>> meditations = new MutableLiveData<>();

    public LiveData<List<Meditation>> getMeditations() {
        if (meditations.getValue() == null) {
            loadMeditations();
        }
        return meditations;
    }

    private void loadMeditations() {
        List<Meditation> list = new ArrayList<>();
        list.add(new Meditation("Morning Zen", "Meditation", "10 mins"));
        list.add(new Meditation("Deep Sleep", "Sleep", "25 mins"));
        list.add(new Meditation("Focus Flow", "Focus", "15 mins"));
        list.add(new Meditation("Stress Release", "Meditation", "20 mins"));
        meditations.setValue(list);
    }
}