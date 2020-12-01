package com.example.ktuhot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_material);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String name = getIntent().getStringExtra("Name");
        String description = getIntent().getStringExtra("Description");
        String coefficient = getIntent().getStringExtra("Coefficient");
        String imageUrl = getIntent().getStringExtra("ImageUrl");

        TextView materialName = findViewById(R.id.materialName);
        TextView materialDescription = findViewById(R.id.materialDescription);
        TextView materialCoefficient = findViewById(R.id.materialCoefficient);
        materialName.setText(name);
        materialDescription.setText(description);
        materialCoefficient.setText(coefficient);

        ImageView materialImage = findViewById(R.id.materialImage);
        Picasso.get().load(imageUrl).into(materialImage);
    }
}