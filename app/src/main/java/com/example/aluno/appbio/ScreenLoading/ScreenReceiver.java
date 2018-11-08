package com.example.aluno.appbio.ScreenLoading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aluno.appbio.Interface.ModoEstudo.MostrarAssuntos;
import com.example.aluno.appbio.Interface.ModoEstudo.MostrarConteudos;
import com.example.aluno.appbio.Interface.TelaPrincipal;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Repository.AssuntoRepository;

import java.util.Collections;
import java.util.List;

public class ScreenReceiver extends BroadcastReceiver {
    ScreenReceiver screen;
    Context context = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON) || intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) || intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {

            List<Assunto> assuntosAtivos = null;
            try {
                assuntosAtivos = new ProcurarAssuntosAtivos().execute().get();
            } catch (Exception e) {
                Log.e("DEU ERRO NESSA BAGAÇA", e.getMessage());
                e.printStackTrace();
            }
            Collections.shuffle(assuntosAtivos);
            Log.e("ASSUNTOS", assuntosAtivos.toString());
            Log.e("ASSUNTO 0", assuntosAtivos.get(0).toString());
            Intent i = new Intent(context, TelaPrincipal.class);
            intent.putExtra("assuntoId", assuntosAtivos.get(0).getId());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }


    private class ProcurarAssuntosAtivos extends AsyncTask<Void, Void, List<Assunto>> {

        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAllAtivos(context);
            } catch (Exception e) {
                Log.e("DEU ERRO NESSA ASYNC", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}