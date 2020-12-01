package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {


    Button skaiciuoti;
    TextView Koficient;
    EditText ilgis1;
    EditText ilgis2;
    EditText t1;
    EditText t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_calculator);

        ilgis1 = findViewById(R.id.pirminisilgis);
        t1 = findViewById(R.id.pirmineT);
        ilgis2 =findViewById(R.id.NaujasIlgis);
        t2 = findViewById(R.id.NaujaT);
        skaiciuoti =findViewById(R.id.Skaičiuoti);
        Koficient = findViewById(R.id.Kof);

        skaiciuoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double il1,il2;
                double temp1,temp2;

                il1 = Double.parseDouble(String.valueOf(ilgis1.getText()));
                il2 = Double.parseDouble(String.valueOf(ilgis2.getText()));
                temp1 = Double.parseDouble(String.valueOf(t1.getText()));
                temp2 = Double.parseDouble(String.valueOf(t2.getText()));

                double ld =  Math.abs(il2-il1);
                double td = Math.abs(temp2-temp1);

                double kof = ld/(il1*td);

                Koficient.setText("Gautas kietojo kūno augimo \n koficientas:"+formatInScientificNotation(kof)+" M/M*"+'\u2103');
            }
        });

    }
    public static String formatInScientificNotation(double value) {
        NumberFormat baseFormat = NumberFormat.getInstance(Locale.ENGLISH);
        baseFormat.setMinimumFractionDigits(1);

        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return baseFormat.format(value);
        }

        double exp = Math.log10(Math.abs(value));
        exp = Math.floor(exp);

        double base = value / Math.pow(10, exp);

        String power = String.valueOf((long) exp);

        StringBuilder s = new StringBuilder();
        s.append(baseFormat.format(base));
        s.append("\u00d710");

        int len = power.length();
        for (int i = 0; i < len; i++) {
            char c = power.charAt(i);
            switch (c) {
                case '-':
                    s.append('\u207b');
                    break;
                case '1':
                    s.append('\u00b9');
                    break;
                case '2':
                    s.append('\u00b2');
                    break;
                case '3':
                    s.append('\u00b3');
                    break;
                default:
                    s.append((char) (0x2070 + (c - '0')));
                    break;
            }
        }

        return s.toString();
    }
}