package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_leaderboard);
    }
}