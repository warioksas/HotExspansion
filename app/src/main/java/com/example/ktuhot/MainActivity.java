package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.zaisti);
        Button b2 = (Button) findViewById(R.id.nustatymai);
        Button b3 = (Button) findViewById(R.id.shop);
        Button b4 = (Button) findViewById(R.id.lyderiai);
        Button b5 = (Button) findViewById(R.id.help);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    //implement the onClick method here
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.zaisti:
                intent = new Intent(this, Game.class);
                startActivity(intent);
                break;
            case R.id.nustatymai:
                intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
            case R.id.shop:
                intent = new Intent(this, Shop.class);
                startActivity(intent);
                break;
            case R.id.lyderiai:
                intent = new Intent(this, Leaderboard.class);
                startActivity(intent);
                break;
            case R.id.help:
                intent = new Intent(this, Help.class);
                startActivity(intent);
                break;
        }
    }
}