package com.example.mindshield.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mindshield.data.model.Journal;

import java.util.List;

@Dao
public interface JournalDao {
    @Insert
    void insert(Journal journal);

    @Delete
    void delete(Journal journal);

    @Query("SELECT * FROM journals ORDER BY id DESC")
    List<Journal> getAllJournals();
}
