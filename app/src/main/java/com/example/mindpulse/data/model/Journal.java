package com.example.mindshield.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "journals")
public class Journal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String mood;
    private String journalText;
    private String date;

    public Journal(String mood, String journalText, String date) {
        this.mood = mood;
        this.journalText = journalText;
        this.date = date;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public String getJournalText() { return journalText; }
    public void setJournalText(String journalText) { this.journalText = journalText; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
