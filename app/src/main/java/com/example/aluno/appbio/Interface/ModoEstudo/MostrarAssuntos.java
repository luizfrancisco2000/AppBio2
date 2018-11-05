package com.example.aluno.appbio.Interface.ModoEstudo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.appbio.Adapter.AssuntoListAdapter;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class MostrarAssuntos extends AppCompatActivity {

    @BindView(R.id.list_assuntos)
    public ListView listViewAssuntos;

    @BindView(R.id.txt_filtro_assunto)
    EditText txtFiltro;

    private List<Assunto> assuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_assuntos);

        ButterKnife.bind(this);

        popularTela();
    }

    @OnTextChanged(R.id.txt_filtro_assunto)
    public void popularTela() {
        String filtro = txtFiltro.getText().toString().trim();

        if (filtro.equals("")) {
            try {
                assuntos = new ProcurarAssuntos().execute().get();
            } catch (Exception e) {
                Toast.makeText(this, "Houve um erro ao buscar os assuntos!", Toast.LENGTH_SHORT).show();
                Log.e("ERRO ASSUNTOS", e.getMessage());
                e.printStackTrace();
                finish();
            }
        } else {

            try {
                assuntos = new ProcurarAssuntosPorFiltro().execute(filtro).get();
            } catch (Exception e) {
                Toast.makeText(this, "Houve um erro ao buscar os assuntos!", Toast.LENGTH_SHORT).show();
                Log.e("ERRO ASSUNTOS", e.getMessage());
                e.printStackTrace();
                finish();
            }
        }

        AssuntoListAdapter adapter = new AssuntoListAdapter(this, this, assuntos);
        listViewAssuntos.setAdapter(adapter);
    }

    @OnItemClick(R.id.list_assuntos)
    public void listarPorAssuntoSelecionado(int position) {
        Intent intent = new Intent(this, MostrarConteudos.class);
        intent.putExtra("assunto", assuntos.get(position));
        startActivity(intent);
    }


    private class ProcurarAssuntos extends AsyncTask<Void, Void, List<Assunto>> {
        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAll(MostrarAssuntos.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }


    private class ProcurarAssuntosPorFiltro extends AsyncTask<String, Void, List<Assunto>> {
        @Override
        protected List<Assunto> doInBackground(String... strings) {
            try {
                return AssuntoRepository.pesquisa(strings[0], MostrarAssuntos.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
