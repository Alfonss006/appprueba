package com.example.dhoser.rapido;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private static final long tiempo=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, Login_D.class));
                finish();
            }
        };
        Timer timer =new Timer();
        timer.schedule(tarea,tiempo);
    }
}
