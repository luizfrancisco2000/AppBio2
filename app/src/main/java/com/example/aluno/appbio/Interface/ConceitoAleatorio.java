package com.example.aluno.appbio.Interface;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;
import com.example.aluno.appbio.Repository.ConteudoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class ConceitoAleatorio extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lbl_caracteristica)
    public TextView lblCaracteristica;

    @BindViews({R.id.lbl_conceito1, R.id.lbl_conceito2, R.id.lbl_conceito3})
    public List<TextView> lblConceitos;

    @BindView(R.id.lbl_nome_assunto)
    public TextView lblAssunto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceito_aleatorio);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        selecionarConceito();
    }

    private void selecionarConceito() {
        List<Assunto> assuntos = new ArrayList<>();
        List<Conteudo> conteudos = new ArrayList<>();

        try {
            assuntos = new ProcurarAssuntos().execute().get();
        } catch (Exception e) {
            Log.e("ERRO POPULAR TELA", e.getMessage());
            e.printStackTrace();
            finish();
        }

        Collections.shuffle(assuntos);

        Random rand = new Random();

        Assunto assunto = new Assunto();
        try {
            assunto = assuntos.get(rand.nextInt(assuntos.size()));
        } catch (Exception e) {
            Intent intent = new Intent(this, TelaPrincipal.class);
            startActivity(intent);
        }

        try {
            conteudos = new ProcurarConteudos().execute(assunto.getId()).get();
        } catch (Exception e) {
            Log.e("ERRO POPULAR TELA", e.getMessage());
            e.printStackTrace();
            finish();
        }

        Collections.shuffle(conteudos);

        Conteudo conteudo = conteudos.get(rand.nextInt(conteudos.size()));

        popularTela(assunto.getNome(), conteudo);

    }

    private void popularTela(String assuntoNome, Conteudo conteudo) {

        String[] conceitos = conteudo.getConceito().split(",");

        int length = conceitos.length;

        switch (length) {
            case 1:
                lblConceitos.get(0).setText(conceitos[0]);
                lblConceitos.get(1).setText("");
                lblConceitos.get(2).setText("");
                break;
            case 2:
                lblConceitos.get(0).setText(conceitos[0]);
                lblConceitos.get(1).setText(conceitos[1]);
                lblConceitos.get(2).setText("");
                break;
            case 3:
                lblConceitos.get(0).setText(conceitos[0]);
                lblConceitos.get(1).setText(conceitos[1]);
                lblConceitos.get(2).setText(conceitos[2]);
            default:
                break;
        }

        lblAssunto.setText(assuntoNome);
        lblCaracteristica.setText(conteudo.getCaracteristica());

    }

    @Override
    protected void onResume() {
        super.onResume();
        selecionarConceito();
    }


    private class ProcurarAssuntos extends AsyncTask<Void, Void, List<Assunto>> {
        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAllAtivos(ConceitoAleatorio.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    private class ProcurarConteudos extends AsyncTask<Long, Void, List<Conteudo>> {

        @Override
        protected List<Conteudo> doInBackground(Long... longs) {
            try {
                return ConteudoRepository.listarPorAssunto(longs[0], ConceitoAleatorio.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
