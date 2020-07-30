package com.example.eggtimer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    Boolean counterIsActive=false;
    TextView textView;
    SeekBar seekBar;
    Button button;

    @SuppressLint("SetTextI18n")
    public void ButtonPressed(View view){
        if (counterIsActive) {

            textView.setText("0:30");
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            countDownTimer.cancel();
            button.setText("Go!");
            counterIsActive=false;
        }
        else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("Stop");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }
                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.simion);
                    mediaPlayer.start();
                }
            }.start();
        }
    }

    @SuppressLint("SetTextI18n")
    public  void updateTimer(int secondsLeft){
        int minutes = secondsLeft/ 60;
        int seconds= secondsLeft-(minutes*60);
        String SecondString = Integer.toString(seconds);
        if (seconds<=9) {
            SecondString = "0" + SecondString;
        }
        textView.setText(minutes +":"+SecondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView2);
        button=findViewById(R.id.button);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
    }
}
