package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class GameSettingsActivity extends AppCompatActivity implements View.OnClickListener{


    List<String> Medziagos = new ArrayList<>();
    Spinner listas;
    SeekBar slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesettings);
        getSupportActionBar().hide();
        listas = findViewById(R.id.spinner2);
        slide = findViewById(R.id.seekBar);

        Medziagos.add("Volframas");
        Medziagos.add("Deimantas");
        Medziagos.add("Betonas");
        Medziagos.add("Auksas");
        Medziagos.add("Varis");
        Medziagos.add("Stiklas");
        Medziagos.add("politelenas");
        ArrayAdapter<String> medadapter = new ArrayAdapter<String>(GameSettingsActivity.this,R.layout.support_simple_spinner_dropdown_item, Medziagos);
        medadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listas.setAdapter(medadapter);
        Button b1 = (Button) findViewById(R.id.Go);
        b1.setOnClickListener(this);
        // b1.setText(
        //      listas.getSelectedItemPosition());


    }

    public void onClick(View v) {
        Intent intent;
        intent = new Intent(this,GameActivity.class);
        int Kelintas  = listas.getSelectedItemPosition();
        int laikas = slide.getProgress()+3000;
        intent.putExtra("Kelintas",Kelintas);
        intent.putExtra("Laikas",laikas);
        startActivity(intent);
    }
}