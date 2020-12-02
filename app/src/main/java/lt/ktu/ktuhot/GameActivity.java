package lt.ktu.ktuhot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements View.OnTouchListener {

    Random random = new Random();
    ImageView fire, cold, zW, bW, sW, iW;
    TextView laikrodis, Lvl;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    int N = 10000, Laikas, lv = 1, didejimokof=3, tikslumas = 150, Mazejimaslaiko = 100;

    private int rezultatas = 0;
    private long mTimeLeftInMillis = N;
    Button zaisti;
    ProgressBar progresbar;
    ObjectAnimator objanim;
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
        kofai.add(4);
        kofai.add(1);
        kofai.add(12);
        kofai.add(14);
        kofai.add(16);
        kofai.add(8);

        fire.setOnTouchListener(GameActivity.this);
        cold.setOnTouchListener(GameActivity.this);
        zaisti.setOnTouchListener(GameActivity.this);

        hideCircles();
        Lvl.setVisibility(View.GONE);

        int insId = getIntent().getIntExtra("Kelintas", 0);
        Laikas = getIntent().getIntExtra("Laikas", 0);
        N = Laikas;
        didejimokof = kofai.get(insId);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Ar norite palikti žaidimą?");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Taip",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mCountDownTimer.cancel();
                        mCountDownTimer = null;
                        Intent myIntent = new Intent(GameActivity.this, GameSettingsActivity.class);
                        GameActivity.this.startActivity(myIntent);
                        GameActivity.this.finish();
                    }
                });
        builder.setNegativeButton(
                "Ne",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
                    int size = random.nextInt(800) + tikslumas + 50;
                    bW.getLayoutParams().height = size;
                    bW.getLayoutParams().width = size;
                    sW.getLayoutParams().height = size - tikslumas;
                    sW.getLayoutParams().width = size - tikslumas;
                    zW.getLayoutParams().height = size + tikslumas;
                    zW.getLayoutParams().width = size + tikslumas;
                    hideCircles();
                    showCircles();
                    lv++;
                    Lvl.setText("Lygis : "+String.valueOf(lv));
                    if (tikslumas >=50)
                    {
                        tikslumas = (int)(tikslumas * 0.8);
                    }
                    Lvl.setVisibility(View.VISIBLE);
                }
                else {
                    laikrodis.setText("00:00:000");
                    zaisti.setVisibility(View.VISIBLE);
                    Lvl.setVisibility(View.GONE);
                    mTimerRunning = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("Žaidimo pabaiga!");
                    builder.setMessage("Žaidimo metu surinkta taškų: " +String.valueOf(rezultatas) +
                            "\nPasiektas lygmuo: " + lv +
                            "\nGalite pradėti žaidimą iš naujo!");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "Gerai",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
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
                    N = Laikas;
                    mTimeLeftInMillis = N;
                    rezultatas = 0;
                    lv = 1;
                    tikslumas = 150;
                    objanim = ObjectAnimator.ofInt(progresbar, "progress", 0, 100);
                    objanim.setDuration(N);
                    startTimer();
                    objanim.start();
                    int size = random.nextInt(800) + tikslumas + 50;
                    iW.getLayoutParams().height = 300;
                    iW.getLayoutParams().width = 300;
                    bW.getLayoutParams().height = size;
                    bW.getLayoutParams().width = size;
                    sW.getLayoutParams().height = size - tikslumas;
                    sW.getLayoutParams().width = size - tikslumas;
                    zW.getLayoutParams().height = size + tikslumas;
                    zW.getLayoutParams().width = size + tikslumas;
                    zaisti.setVisibility(View.GONE);
                    showCircles();
                    Lvl.setVisibility(View.VISIBLE);
                    Lvl.setText("Lygis: 1");
                }
                break;
            case R.id.fire:

                if (1000 >= iW.getHeight() + didejimokof && mTimerRunning) {
                    iW.getLayoutParams().height = iW.getHeight() + didejimokof;
                    iW.getLayoutParams().width = iW.getWidth() + didejimokof;
                }
                break;
            case R.id.cold:
                if (iW.getHeight() - didejimokof >= 50 + tikslumas && mTimerRunning) {
                    iW.getLayoutParams().height = iW.getHeight() - didejimokof;
                    iW.getLayoutParams().width = iW.getWidth() - didejimokof;
                }
                break;
        }
        return true;
    }

    private void hideCircles()
    {
        iW.setVisibility(View.GONE);
        zW.setVisibility(View.GONE);
        sW.setVisibility(View.GONE);
        bW.setVisibility(View.GONE);
        Lvl.setVisibility(View.GONE);
    }

    private void showCircles()
    {
        iW.setVisibility(View.VISIBLE);
        zW.setVisibility(View.VISIBLE);
        sW.setVisibility(View.VISIBLE);
        bW.setVisibility(View.VISIBLE);
    }
}