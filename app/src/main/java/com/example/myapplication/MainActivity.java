package com.example.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnAdd5, btnAdd15, btnAdd30;
    Button btnRem5, btnRem15, btnRem30;
    Button btnStart, btnPause, btnReset;
    long startingTime, elapsedTime = 0;
    Boolean isRunning = false;
    Boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chronometer = findViewById(R.id.timer);

        btnAdd5 = findViewById(R.id.btnAdd5);
        btnAdd15 = findViewById(R.id.btnAdd15);
        btnAdd30 = findViewById(R.id.btnAdd30);

        btnRem5 = findViewById(R.id.btnRem5);
        btnRem15 = findViewById(R.id.btnRem15);
        btnRem30 = findViewById(R.id.btnRem30);

        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);

        btnRem5.setEnabled(false);
        btnRem15.setEnabled(false);
        btnRem30.setEnabled(false);
        btnStart.setEnabled(false);
        btnPause.setEnabled(false);
        btnReset.setEnabled(false);

        btnAdd5.setOnClickListener(view -> addToTimer(5));
        btnAdd15.setOnClickListener(view -> addToTimer(15));
        btnAdd30.setOnClickListener(view -> addToTimer(30));

        btnRem5.setOnClickListener(view -> addToTimer(-5));
        btnRem15.setOnClickListener(view -> addToTimer(-15));
        btnRem30.setOnClickListener(view -> addToTimer(-30));

        btnStart.setOnClickListener(view -> startTimer());
        btnReset.setOnClickListener(view -> stopTimer());
        btnPause.setOnClickListener(view -> pauseTimer());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime() >= chronometer.getBase()) && isRunning){
                    stopTimer();
                }
            }
        });
    }
    private void pauseTimer(){
        if (isRunning) {
            elapsedTime = chronometer.getBase() - SystemClock.elapsedRealtime(); // Save remaining time
            chronometer.stop();
            isRunning = false;
            isPaused = true;

            btnStart.setEnabled(true);  // Allow resuming
            btnPause.setEnabled(false);
        }
    }
    private void stopTimer(){
        chronometer.stop();
        isRunning = false;
        isPaused = false;
        elapsedTime = 0;

        btnReset.setEnabled(false);
        btnPause.setEnabled(false);

        btnAdd5.setEnabled(true);
        btnAdd15.setEnabled(true);
        btnAdd30.setEnabled(true);

        startingTime = 0;
        chronometer.setCountDown(false);
        chronometer.setBase(SystemClock.elapsedRealtime() + startingTime * 1000 );
    }
    private void startTimer(){
        if (isPaused){
            chronometer.setBase(SystemClock.elapsedRealtime() + elapsedTime);
            isPaused = false;
        }
        else {
            chronometer.setBase(SystemClock.elapsedRealtime() + startingTime * 1000);
        }

        isRunning = true;
        chronometer.setCountDown(true);
        chronometer.start();

        btnStart.setEnabled(false);
        btnReset.setEnabled(true);
        btnPause.setEnabled(true);

        btnAdd5.setEnabled(false);
        btnAdd15.setEnabled(false);
        btnAdd30.setEnabled(false);

        btnRem5.setEnabled(false);
        btnRem15.setEnabled(false);
        btnRem30.setEnabled(false);
    }
    private void addToTimer(long time){
        startingTime += time;
        chronometer.setBase(SystemClock.elapsedRealtime() - startingTime * 1000);
        handleChangeButtons();
    }

    private void handleChangeButtons(){
        if (!isRunning){
            btnRem5.setEnabled(startingTime >= 5);
            btnRem15.setEnabled(startingTime >= 15);
            btnRem30.setEnabled(startingTime >= 30);
        }
        btnStart.setEnabled(startingTime > 0);
    }
}