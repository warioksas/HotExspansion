package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.game);
        Button b3 = (Button) findViewById(R.id.aboutApp);
        Button b4 = (Button) findViewById(R.id.aboutMaterials);
        Button b5 = (Button) findViewById(R.id.calculator);

        b1.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    //implement the onClick method here
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.game:
                intent = new Intent(this, GameSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.aboutApp:
                intent = new Intent(this, AboutAppAcitvity.class);
                startActivity(intent);
                break;
            case R.id.aboutMaterials:
                intent = new Intent(this, AboutMaterialsActivity.class);
                startActivity(intent);
                break;
            case R.id.calculator:
                intent = new Intent(this, CalculatorActivity.class);
                startActivity(intent);
                break;
        }
    }
}