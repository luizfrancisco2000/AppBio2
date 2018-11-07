package com.example.aluno.appbio.Interface.ModoEstudo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.appbio.Adapter.ConteudoListAdapter;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Conteudo;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.ConteudoRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class MostrarConteudos extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txt_filtro_conteudo)
    EditText txtFiltro;

    @BindView(R.id.list_conteudos)
    public ListView listViewConteudos;

    private List<Conteudo> conteudos;

    private Assunto assunto;

    private String filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_conteudos);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        try {
            Intent intent = getIntent();
            assunto = (Assunto) intent.getSerializableExtra("assunto");
        } catch (Exception e) {
            Toast.makeText(this, "Ocorreu um erro ao listar os conteudos", Toast.LENGTH_SHORT);
            Log.e("ERRO INTENT ASSUNTO", e.getMessage());
            e.printStackTrace();
            finish();
        }

        Log.e("ASSUNTO", assunto.toString());

        //popularTela();
    }

    @OnTextChanged(R.id.txt_filtro_conteudo)
    public void popularTela() {

        filtro = txtFiltro.getText().toString().trim();

        if (filtro.equals("")) {
            try {
                conteudos = new ProcurarConteudos().execute(assunto.getId()).get();
            } catch (Exception e) {
                Toast.makeText(this, "Ocorreu um erro ao listar os conteudos", Toast.LENGTH_SHORT).show();
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                finish();
            }
        } else {
            try {
                conteudos = new ProcurarConteudosPorCaracteristica().execute(filtro).get();
            } catch (Exception e) {
                Toast.makeText(this, "Ocorreu um erro ao listar os conteudos", Toast.LENGTH_SHORT).show();
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                finish();
            }
        }

        ConteudoListAdapter adapter = new ConteudoListAdapter(this, this, conteudos);
        listViewConteudos.setAdapter(adapter);
    }

    @OnItemClick(R.id.list_conteudos)
    public void mostrarConteudo(int position) {
        Intent intent = new Intent(this, ConteudoSelecionado.class);
        intent.putExtra("assunto", assunto);
        intent.putExtra("conteudo", conteudos.get(position).getId());
        startActivity(intent);
    }


    private class ProcurarConteudos extends AsyncTask<Long, Void, List<Conteudo>> {

        @Override
        protected List<Conteudo> doInBackground(Long... longs) {
            try {
                return ConteudoRepository.listarPorAssunto(longs[0], MostrarConteudos.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

    private class ProcurarConteudosPorCaracteristica extends AsyncTask<String, Void, List<Conteudo>> {

        @Override
        protected List<Conteudo> doInBackground(String... strings) {
            try {
                return ConteudoRepository.listarPorAssuntoECaracTeristica(strings[0], assunto.getId(), MostrarConteudos.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC CONTEUDOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
