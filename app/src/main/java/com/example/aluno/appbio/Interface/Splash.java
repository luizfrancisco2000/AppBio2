package com.example.aluno.appbio.Interface;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.aluno.appbio.R;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);


        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(Splash.this, TelaPrincipal.class);
                startActivity(i);

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
