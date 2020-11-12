package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Resources mResources;
    private ImageView mImageView;
    private ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        Button fire = findViewById(R.id.fire);
        Button cold = findViewById(R.id.cold);
        fire.setOnClickListener(Game.this);
        cold.setOnClickListener(Game.this);

    }

    public void onClick(View v) {
        ImageView iW = findViewById(R.id.circleView);
        iW.requestLayout();
        switch (v.getId()) {
            case R.id.fire:
                iW.getLayoutParams().height = iW.getHeight() + 200;
                iW.getLayoutParams().width = iW.getWidth() + 200;
                break;
            case R.id.cold:
                iW.getLayoutParams().height = iW.getHeight() - 200;
                iW.getLayoutParams().width = iW.getWidth() - 200;
                break;
        }
    }
}