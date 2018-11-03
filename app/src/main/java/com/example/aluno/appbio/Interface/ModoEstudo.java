package com.example.aluno.appbio.Interface;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.appbio.Adapter.AssuntoListAdapter;
import com.example.aluno.appbio.Adapter.ConteudoListAdapter;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;
import com.example.aluno.appbio.Repository.ConteudoRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ModoEstudo extends AppCompatActivity {

    @BindView(R.id.list_assuntos)
    public ListView listViewAssuntos;

    @BindView(R.id.list_conteudos)
    public ListView listViewConteudos;

    private List<Assunto> assuntos;
    private List<Conteudo> conteudos;

    private List<Conteudo> conteudosFiltrados;

    private boolean assuntoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_estudo);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle(R.string.modo_estudo);

        try {
            assuntos = new ProcurarAssuntos().execute().get();
        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao buscar os assuntos!", Toast.LENGTH_SHORT).show();
            Log.e("ERRO ASSUNTOS", e.getMessage());
            e.printStackTrace();
            finish();
        }

        if (assuntos == null) {
            Toast.makeText(this, "Nenhum assunto encontrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            AssuntoListAdapter adapter = new AssuntoListAdapter(this, this, assuntos);
            listViewAssuntos.setAdapter(adapter);
        }

    }


    @OnItemClick(R.id.list_conteudos)
    public void mostrarConteudo(int position) {
        Assunto assunto = assuntos.get(listViewAssuntos.getSelectedItemPosition() + 1);

        Intent intent = new Intent(this, MostrarConteudo.class);
        intent.putExtra("conteudoPosicao", position);
        intent.putExtra("assunto", assunto);
        startActivity(intent);
    }


    @OnItemClick(R.id.list_assuntos)
    public void listarPorAssuntoSelecionado(int position) {
        listViewConteudos.setVisibility(View.VISIBLE);
        listViewAssuntos.setVisibility(View.GONE);

        assuntoSelecionado = true;
        Assunto a = assuntos.get(position);
        try {
            conteudos = new ProcurarConteudos().execute(a.getId()).get();
        } catch (Exception e) {
            Toast.makeText(this, "Houve um erro ao buscar os conteudos!", Toast.LENGTH_SHORT).show();
            Log.e("ERRO CONTEUDOS", e.getMessage());
            e.printStackTrace();
            finish();
        }

        if (conteudos == null) {
            Toast.makeText(this, "Nenhum conteudo encontrado!", Toast.LENGTH_SHORT).show();
        } else {
            ConteudoListAdapter adapter = new ConteudoListAdapter(this, this, conteudos);
            listViewConteudos.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        if (assuntoSelecionado) {
            listViewConteudos.setVisibility(View.GONE);
            listViewAssuntos.setVisibility(View.VISIBLE);
            assuntoSelecionado = false;
        } else {
            finish();
        }
    }

    private class ProcurarAssuntos extends AsyncTask<Void, Void, List<Assunto>> {
        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAll(ModoEstudo.this);
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
                return ConteudoRepository.listarPorAssunto(longs[0], ModoEstudo.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
