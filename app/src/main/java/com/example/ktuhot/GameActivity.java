package com.example.ktuhot;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

    Random random = new Random();
    ImageView fire;
    ImageView cold;
    ImageView zW;
    ImageView bW;
    ImageView sW;
    ImageView iW;
    TextView raudonas;
    TextView zalias;
    TextView judantis;
    TextView laikrodis;
    TextView Rez;
    private CountDownTimer mCountDownTimer ;
    private boolean mTimerRunning;
    int N = 10000;
    private long mTimeLeftInMillis = N;
    Button zaisti;
    ProgressBar progresbar;
    ObjectAnimator objanim;


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
        raudonas = findViewById(R.id.raudonas);
        zalias = findViewById(R.id.zalias);
        judantis = findViewById(R.id.judantis);
        Rez = findViewById(R.id.rez);
        laikrodis  = findViewById(R.id.Laikas);
        zaisti =findViewById(R.id.Žaisti);
        zaisti.setOnTouchListener(GameActivity.this);

        progresbar = findViewById(R.id.progressBar);



        objanim = ObjectAnimator.ofInt(progresbar,"progress",0,100);
        objanim.setDuration(N);



        fire.setOnTouchListener(GameActivity.this);
        cold.setOnTouchListener(GameActivity.this);
        /*objanim.start();

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
*/
    }

    private void startTimer() {
        mCountDownTimer  = new CountDownTimer(mTimeLeftInMillis, 25) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mTimerRunning = true;
            }
            @Override
            public void onFinish() {
                N = N- 100;// sekancio periodo laikas
                int score = Math.abs(iW.getLayoutParams().height - bW.getLayoutParams().height);
                if(score <= 50) {
                    mTimeLeftInMillis = N;// naujo laiko intervalas
                    startTimer();// laikmacio paleidimas sekanciam runui
                    objanim.setDuration(N);
                    objanim.start();// animacija progres baro
                    int size = random.nextInt(900) + 100;
                    Rez.setText(String.valueOf(score));
                    bW.getLayoutParams().height = size;
                    bW.getLayoutParams().width = size;
                    sW.getLayoutParams().height = size - 50;
                    sW.getLayoutParams().width = size - 50;
                    zW.getLayoutParams().height = size + 50;
                    zW.getLayoutParams().width = size + 50;

                    zalias.setText(String.valueOf(bW.getLayoutParams().height));

                }
                else {
                    Rez.setText(String.valueOf(score));
                    laikrodis.setText("Game Over");
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

    public boolean onTouch(View v, MotionEvent event) {
        iW.requestLayout();

        switch (v.getId()) {
            case R.id.Žaisti:
                startTimer();
                objanim.start();
                int size = random.nextInt(900) + 100;
                iW.getLayoutParams().height = 100;
                iW.getLayoutParams().width = 100;
                bW.getLayoutParams().height = size;
                bW.getLayoutParams().width = size;
                sW.getLayoutParams().height = size - 50;
                sW.getLayoutParams().width = size - 50;
                zW.getLayoutParams().height = size + 50;
                zW.getLayoutParams().width = size + 50;

                raudonas.setText(String.valueOf(sW.getLayoutParams().height));
                zalias.setText(String.valueOf(bW.getLayoutParams().height));
                judantis.setText(String.valueOf(iW.getLayoutParams().height));
                zaisti.setVisibility(View.GONE);






                break;
            case R.id.fire:
                if (1000 >= iW.getHeight() + 1 && mTimerRunning) {
                    iW.getLayoutParams().height = iW.getHeight() + 1;
                    iW.getLayoutParams().width = iW.getWidth() + 1;
                    judantis.setText(String.valueOf(iW.getLayoutParams().height));
                }
                break;
            case R.id.cold:
                if (iW.getHeight() - 50 >= 50 &&mTimerRunning) {
                    iW.getLayoutParams().height = iW.getHeight() - 1;
                    iW.getLayoutParams().width = iW.getWidth() - 1;
                    judantis.setText(String.valueOf(iW.getLayoutParams().height));
                }
                break;


        }
     return true;
    }

}