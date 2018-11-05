package com.example.aluno.appbio.Interface.ModoEstudo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.ConteudoRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConteudoSelecionado extends AppCompatActivity {

    @BindView(R.id.lbl_caracteristica)
    public TextView lblCaracteristica;

    @BindViews({R.id.lbl_conceito1, R.id.lbl_conceito2, R.id.lbl_conceito3})
    public List<TextView> lblConceitos;

    @BindView(R.id.lbl_nome_assunto)
    public TextView lblAssunto;

    private int posicaoConteudo;
    private Assunto assunto;
    private List<Conteudo> conteudos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_selecionado);

        long conteudo_id = 0;

        try {
            Intent intent = getIntent();
            assunto = (Assunto) intent.getSerializableExtra("assunto");
            conteudo_id = intent.getLongExtra("conteudo", 0);
            conteudos = new ProcurarConteudos().execute(assunto.getId()).get();
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível mostrar o conteúdo selecionado!", Toast.LENGTH_LONG).show();
            Log.e("ERRO INTENT", e.getMessage());
            e.printStackTrace();
            finish();
        }

        for (int i = 0; i < conteudos.size(); i++) {
            if (conteudos.get(i).getId() == conteudo_id) {
                posicaoConteudo = i;
            }
        }

        ButterKnife.bind(this);

        popularTela();

    }

    private void popularTela() {
        Conteudo conteudo = conteudos.get(posicaoConteudo);

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

        lblAssunto.setText(assunto.getNome());
        lblCaracteristica.setText(conteudo.getCaracteristica());

    }

    @OnClick(R.id.btn_anterior)
    public void anterior() {
        if (posicaoConteudo > 0) {
            posicaoConteudo--;
            popularTela();
        } else {
            Toast.makeText(this, "Primeiro conteúdo cadastrado!", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.btn_proximo)
    public void proximo() {
        posicaoConteudo++;
        if (posicaoConteudo < conteudos.size()) {
            popularTela();
        } else {
            posicaoConteudo--;
            Toast.makeText(this, "Último conteúdo cadastrado!", Toast.LENGTH_SHORT).show();
        }
    }

    private class ProcurarConteudos extends AsyncTask<Long, Void, List<Conteudo>> {

        @Override
        protected List<Conteudo> doInBackground(Long... longs) {
            try {
                return ConteudoRepository.listarPorAssunto(longs[0], ConteudoSelecionado.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
