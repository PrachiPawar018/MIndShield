package com.example.mindpulse.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mindpulse.data.model.Meditation;

import java.util.ArrayList;
import java.util.List;

public class MeditationRepository {
    
    private static MeditationRepository instance;
    
    public static synchronized MeditationRepository getInstance() {
        if (instance == null) {
            instance = new MeditationRepository();
        }
        return instance;
    }

    public LiveData<List<Meditation>> getMeditations() {
        MutableLiveData<List<Meditation>> data = new MutableLiveData<>();
        List<Meditation> list = new ArrayList<>();
        list.add(new Meditation("Morning Zen", "Meditation", "10 mins"));
        list.add(new Meditation("Deep Sleep", "Sleep", "25 mins"));
        list.add(new Meditation("Focus Flow", "Focus", "15 mins"));
        list.add(new Meditation("Stress Release", "Meditation", "20 mins"));
        list.add(new Meditation("Ocean Waves", "Nature", "30 mins"));
        list.add(new Meditation("Mindful Breath", "Meditation", "5 mins"));
        data.setValue(list);
        return data;
    }
}