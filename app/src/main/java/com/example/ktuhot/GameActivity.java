package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements View.OnTouchListener {

    Random random = new Random();
    ImageView fire;
    ImageView cold;
    ImageView zW;
    ImageView bW;
    ImageView sW;
    ImageView iW;
    TextView laikrodis;
    TextView Rez;
    TextView Lvl;
    private CountDownTimer mCountDownTimer ;
    private boolean mTimerRunning;
    int N = 10000;
    int Laikas;
    int lv = 1;

    int didejimokof=3;
    int tikslumas = 150;
    int Mazejimaslaiko = 100;

    private int rezultatas = 0;
    private long mTimeLeftInMillis = N;
    Button zaisti;
    ProgressBar progresbar;
    ObjectAnimator objanim;
   // Spinner listas;

    // List<String> Medziagos = new ArrayList<>();
    List<Integer> kofai = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        fire = findViewById(R.id.fire);
        cold = findViewById(R.id.cold);
        bW = findViewById(R.id.boundaryView);
        sW = findViewById(R.id.maxCircle);
        zW = findViewById(R.id.MinCircle);
        iW = findViewById(R.id.circleView);
        progresbar = findViewById(R.id.progressBar);
        laikrodis  = findViewById(R.id.Laikas);
        zaisti =findViewById(R.id.Žaisti);
        Lvl = findViewById(R.id.LVL);
        Rez = findViewById(R.id.Rezultatas);
       // listas = findViewById(R.id.spinner);
       // Medziagos.add("Pasirinkite medziaga ");
        kofai.add(4);
      //  Medziagos.add("Deimantas");
        kofai.add(1);
      //  Medziagos.add("Betonas");
        kofai.add(12);
       // Medziagos.add("Auksas");
        kofai.add(14);
      //  Medziagos.add("Varis");
        kofai.add(16);
       // Medziagos.add("Stiklas");
        kofai.add(8);
      //  Medziagos.add("politelenas");
        kofai.add(200);

      /*  ArrayAdapter<String>  medadapter = new ArrayAdapter<String>(GameActivity.this,R.layout.support_simple_spinner_dropdown_item, Medziagos);
        medadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listas.setAdapter(medadapter);

*/

        fire.setOnTouchListener(GameActivity.this);
        cold.setOnTouchListener(GameActivity.this);
        zaisti.setOnTouchListener(GameActivity.this);

        iW.setVisibility(View.GONE);
        zW.setVisibility(View.GONE);
        sW.setVisibility(View.GONE);
        bW.setVisibility(View.GONE);
        Lvl.setVisibility(View.GONE);
        Rez.setVisibility(View.VISIBLE);



        int insId = getIntent().getIntExtra("Kelintas", 0);
        Laikas = getIntent().getIntExtra("Laikas", 0);
        N = Laikas;
        didejimokof = kofai.get(insId);




    }

    private void startTimer() {

        mCountDownTimer  = new CountDownTimer(mTimeLeftInMillis, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mTimerRunning = true;
            }
            @Override
            public void onFinish() {

                int score = Math.abs(iW.getLayoutParams().height - bW.getLayoutParams().height);

                if(score <= tikslumas) {

                    N = N-  (int)(1000 * (score/tikslumas*1.00))+50;
                    rezultatas += (tikslumas-score)*lv;
                    mTimeLeftInMillis = N;// naujo laiko intervalas
                    startTimer();// laikmacio paleidimas sekanciam runui
                    objanim.setDuration(N);
                    objanim.start();// animacija progres baro
                    int size = random.nextInt(900) + 100;

                    bW.getLayoutParams().height = size;
                    bW.getLayoutParams().width = size;
                    sW.getLayoutParams().height = size - tikslumas;
                    sW.getLayoutParams().width = size - tikslumas;
                    zW.getLayoutParams().height = size + tikslumas;
                    zW.getLayoutParams().width = size + tikslumas;
                    lv++;
                    Lvl.setText("LVL : "+String.valueOf(lv));
                    if (tikslumas >=50  ){tikslumas = (int)(tikslumas * 0.8);}

                }
                else {
                    Rez.setVisibility(View.VISIBLE);
                    Rez.setText("Rezultatas : "+String.valueOf(rezultatas) );
                    laikrodis.setText("Game Over");
                    zaisti.setText("Play Again");
                    zaisti.setVisibility(View.VISIBLE);
                    mTimerRunning = false;
                }
            }

        }.start();


    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        int milis = (int) (mTimeLeftInMillis%1000);
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", minutes, seconds,milis);
        laikrodis.setText(timeLeftFormatted);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        iW.requestLayout();
        switch (v.getId()) {
            case R.id.Žaisti:
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    N =Laikas;
                    mTimeLeftInMillis = N;
                    rezultatas = 0;

                     lv = 1;

                    // didejimokof=3;
                     tikslumas = 150;
                    objanim = ObjectAnimator.ofInt(progresbar, "progress", 0, 100);
                objanim.setDuration(N);
                startTimer();
                objanim.start();
                int size = random.nextInt(900) + 200;
                iW.getLayoutParams().height = 300;
                iW.getLayoutParams().width = 300;
                bW.getLayoutParams().height = size;
                bW.getLayoutParams().width = size;
                sW.getLayoutParams().height = size - tikslumas;
                sW.getLayoutParams().width = size - tikslumas;
                zW.getLayoutParams().height = size + tikslumas;
                zW.getLayoutParams().width = size + tikslumas;
                zaisti.setVisibility(View.GONE);
                    iW.setVisibility(View.VISIBLE);
                    zW.setVisibility(View.VISIBLE);
                    sW.setVisibility(View.VISIBLE);
                    bW.setVisibility(View.VISIBLE);
                    Lvl.setVisibility(View.VISIBLE);
                    Rez.setVisibility(View.GONE);
                    Lvl.setText("LVL 1");
                  //  int Kelintas  = (int)listas.getSelectedItemId();
                 //   didejimokof = kofai.get(Kelintas);
                  }
                break;
            case R.id.fire:

                if (1000 >= iW.getHeight() + didejimokof && mTimerRunning) {
                    iW.getLayoutParams().height = iW.getHeight() + didejimokof;
                    iW.getLayoutParams().width = iW.getWidth() + didejimokof;
                    //judantis.setText(String.valueOf(iW.getLayoutParams().height));
                }
                break;
            case R.id.cold:
                if (iW.getHeight() - 50 >= 50 &&mTimerRunning) {
                    iW.getLayoutParams().height = iW.getHeight() - didejimokof;
                    iW.getLayoutParams().width = iW.getWidth() - didejimokof;
                    //judantis.setText(String.valueOf(iW.getLayoutParams().height));
                }
                break;


        }
     return true;
    }

}