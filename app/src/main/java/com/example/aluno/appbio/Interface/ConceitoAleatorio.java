package com.example.aluno.appbio.Interface;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.OnClick;

public class ConceitoAleatorio extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lbl_caracteristica)
    public TextView lblCaracteristica;

    @BindViews({R.id.lbl_conceito1, R.id.lbl_conceito2, R.id.lbl_conceito3})
    public List<TextView> lblConceitos;

    @BindView(R.id.lbl_nome_assunto)
    public TextView lblAssunto;

    @BindView(R.id.btn_ok)
    public Button btn_ok;

    private List<Assunto> assuntos = new ArrayList<>();
    private List<Conteudo> conteudos = new ArrayList<>();
    private int posAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_selecionado);

        ButterKnife.bind(this);

        btn_ok.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);

        selecionarConceito();
    }

    private void selecionarConceito() {

        try {
            assuntos = new ProcurarAssuntos().execute().get();
        } catch (Exception e) {
            Log.e("ERRO POPULAR TELA", e.getMessage());
            e.printStackTrace();
            finish();
        }

        Collections.shuffle(assuntos);

        Random rand = new Random();

        try {

            for (Assunto assunto : assuntos) {
                conteudos.addAll(new ProcurarConteudos().execute(assunto.getId()).get());
            }

        } catch (Exception e) {
            Log.e("ERRO POPULAR TELA", e.getMessage());
            e.printStackTrace();
            finish();
        }

        Collections.shuffle(conteudos);


        popularTela();

    }

    private void popularTela() {

        Conteudo conteudo = conteudos.get(posAtual);

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

        for (Assunto assunto : assuntos) {
            if (assunto.getId() == conteudo.getAssunto_id()) {
                lblAssunto.setText(assunto.getNome());
                break;
            }
        }

        lblCaracteristica.setText(conteudo.getCaracteristica());

    }

    @OnClick(R.id.btn_anterior)
    public void anterior() {
        if (posAtual > 0) {
            posAtual--;
            popularTela();
        } else {
            Toast.makeText(this, "Primeiro conteúdo cadastrado!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_ok)
    public void sair() {
        finish();
    }

    @OnClick(R.id.btn_proximo)
    public void proximo() {
        posAtual++;
        if (posAtual < conteudos.size()) {
            popularTela();
        } else {
            posAtual--;
            Toast.makeText(this, "Último conteúdo cadastrado!", Toast.LENGTH_SHORT).show();
        }
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
