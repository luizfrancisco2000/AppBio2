package com.example.aluno.appbio.ScreenLoading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.aluno.appbio.Interface.ConceitoAleatorio;

public class ScreenReceiver extends BroadcastReceiver {
    ScreenReceiver screen;
    Context context = null;
    boolean ativo_tela_bloqueio;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            ativo_tela_bloqueio = sharedPreferences.getBoolean("ativo_tela_bloqueio", false);
        } catch (Exception e) {
            Log.e("ERRO INTENT BLOQUEIO", e.getMessage());
            e.printStackTrace();
        }

        if (ativo_tela_bloqueio) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON) || intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) || intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                Intent i = new Intent(context, ConceitoAleatorio.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }


    }
}