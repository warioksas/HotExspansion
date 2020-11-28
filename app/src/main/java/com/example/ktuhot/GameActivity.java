package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Random random = new Random();
    ImageView fire;
    ImageView cold;
    ImageView bW;
    ImageView sW;
    ImageView iW;
    TextView raudonas;
    TextView zalias;
    TextView judantis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        fire = findViewById(R.id.fire);
        cold = findViewById(R.id.cold);
        bW = findViewById(R.id.boundaryView);
        sW = findViewById(R.id.maxCircle);
        iW = findViewById(R.id.circleView);
        raudonas = findViewById(R.id.raudonas);
        zalias = findViewById(R.id.zalias);
        judantis = findViewById(R.id.judantis);
        fire.setOnClickListener(GameActivity.this);
        cold.setOnClickListener(GameActivity.this);
        int size = random.nextInt((900 - 700) + 1) + 700;
        bW.getLayoutParams().height = size;
        bW.getLayoutParams().width = size;
        sW.getLayoutParams().height = 1000;
        sW.getLayoutParams().width = 1000;
        iW.getLayoutParams().height = 100;
        iW.getLayoutParams().width = 100;
        raudonas.setText(String.valueOf(sW.getLayoutParams().height));
        zalias.setText(String.valueOf(bW.getLayoutParams().height));
        judantis.setText(String.valueOf(iW.getLayoutParams().height));
    }

    public void onClick(View v) {
        iW.requestLayout();
        switch (v.getId()) {
            case R.id.fire:
                if (sW.getLayoutParams().height >= iW.getHeight() + 50) {
                    iW.getLayoutParams().height = iW.getHeight() + 50;
                    iW.getLayoutParams().width = iW.getWidth() + 50;
                    judantis.setText(String.valueOf(iW.getLayoutParams().height));
                }
                break;
            case R.id.cold:
                if (iW.getHeight() - 50 >= 100) {
                    iW.getLayoutParams().height = iW.getHeight() - 50;
                    iW.getLayoutParams().width = iW.getWidth() - 50;
                    judantis.setText(String.valueOf(iW.getLayoutParams().height));
                }
                break;
        }
    }
}