package com.example.aluno.appbio.Interface.ModoEstudo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.appbio.Adapter.AssuntoListAdapter;
import com.example.aluno.appbio.Adapter.LaminaListAdapter;
import com.example.aluno.appbio.Model.Assunto;
import com.example.aluno.appbio.Model.Lamina;
import com.example.aluno.appbio.R;
import com.example.aluno.appbio.Repository.AssuntoRepository;
import com.example.aluno.appbio.Repository.LaminaRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class MostrarAssuntos extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list_assuntos)
    public ListView listViewAssuntos;

    @BindView(R.id.list_laminas)
    public ListView listViewLaminas;

    @BindView(R.id.txt_filtro_assunto)
    EditText txtFiltro;

    private List<Assunto> assuntos;
    private List<Lamina> laminas;

    private boolean listar_laminas = false;

    private final static int MOSTRAR_ASSUNTOS = Menu.FIRST;
    private final static int MOSTRAR_LAMINAS = Menu.FIRST + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_assuntos);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        popularListaAssuntos();
    }

    @OnTextChanged(R.id.txt_filtro_assunto)
    public void popularListaAssuntos() {
        String filtro = txtFiltro.getText().toString().trim();

        if (listar_laminas) {
            popularListaLaminas(filtro);
        } else {
            listViewAssuntos.setVisibility(View.VISIBLE);
            listViewLaminas.setVisibility(View.GONE);
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

    }

    private void popularListaLaminas(String filtro) {
        if (filtro.equals("")) {
            try {
                laminas = new ProcurarLaminas().execute().get();
            } catch (Exception e) {
                Toast.makeText(this, "Houve um erro ao buscar as lâminas!", Toast.LENGTH_SHORT).show();
                Log.e("ERRO LÂMINAS", e.getMessage());
                e.printStackTrace();
                finish();
            }
        } else {
            try {
                laminas = new ProcurarLaminasPorFiltro().execute(filtro).get();
            } catch (Exception e) {
                Toast.makeText(this, "Houve um erro ao buscar as lâminas!", Toast.LENGTH_SHORT).show();
                Log.e("ERRO LÂMINAS", e.getMessage());
                e.printStackTrace();
                finish();
            }
        }

        listViewAssuntos.setVisibility(View.GONE);
        listViewLaminas.setVisibility(View.VISIBLE);
        LaminaListAdapter adapter = new LaminaListAdapter(this, this, laminas);
        listViewLaminas.setAdapter(adapter);
    }

    @OnItemClick(R.id.list_assuntos)
    public void listarPorAssuntoSelecionado(int position) {
        Intent intent = new Intent(this, MostrarConteudos.class);
        intent.putExtra("assunto", assuntos.get(position));
        startActivity(intent);
    }

    @OnItemClick(R.id.list_laminas)
    public void exibirLamina(int position) {
        Intent intent = new Intent(this, LaminaSelecionada.class);
        intent.putExtra("lamina", laminas.get(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.add(0, MOSTRAR_ASSUNTOS, 0, "Listar Assuntos");
        menu.add(0, MOSTRAR_LAMINAS, 0, "Listar Lâminas");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case MOSTRAR_ASSUNTOS:
                listar_laminas = false;
                popularListaAssuntos();
                txtFiltro.setHint(R.string.hint_filtro_assunto);
                return true;
            case MOSTRAR_LAMINAS:
                listar_laminas = true;
                popularListaLaminas("");
                txtFiltro.setHint("Número da lâmina");
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    private class ProcurarAssuntos extends AsyncTask<Void, Void, List<Assunto>> {
        @Override
        protected List<Assunto> doInBackground(Void... voids) {
            try {
                return AssuntoRepository.getAllAtivos(MostrarAssuntos.this);
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

    private class ProcurarLaminas extends AsyncTask<Void, Void, List<Lamina>> {
        @Override
        protected List<Lamina> doInBackground(Void... voids) {
            try {
                return LaminaRepository.getAll(MostrarAssuntos.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC LAMINAS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }


    private class ProcurarLaminasPorFiltro extends AsyncTask<String, Void, List<Lamina>> {
        @Override
        protected List<Lamina> doInBackground(String... strings) {
            try {
                int pesquisa = Integer.parseInt(strings[0]);
                return LaminaRepository.pesquisa(pesquisa, MostrarAssuntos.this);
            } catch (Exception e) {
                Log.e("ERRO ASYNC ASSUNTOS", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

}
